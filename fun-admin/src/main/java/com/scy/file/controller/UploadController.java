package com.scy.file.controller;

import com.scy.config.UploadConfig;
import com.scy.file.service.SysFileProcessService;
import com.scy.api.R;
import com.scy.api.Result;
import com.scy.modles.update.MergeDTO;
import com.scy.modles.update.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/upload")
@Slf4j
public class UploadController {
  @Autowired
  private UploadConfig uploadConfig;
  @Autowired
  private ThreadPoolTaskExecutor uploadTaskExecutor;
  @Autowired
  private SysFileProcessService fileProcessService; // 自定义：解压+解析+入库服务

  // 存储任务状态（key：fileMd5，value：任务状态）
  private final ConcurrentHashMap<String, TaskStatus> taskStatusMap = new ConcurrentHashMap<>();

  // 1. 上传分片
  @PostMapping("/shard")
  public R uploadShard(@RequestParam String fileMd5,
                       @RequestParam int shardIndex,
                       @RequestParam int totalShards,
                       @RequestParam String fileName,
                       @RequestParam MultipartFile shard) throws IOException {
    // 创建分片存储目录
    String shardDir = uploadConfig.getShardTempPath(fileMd5);
    File dir = new File(shardDir);
    if (!dir.exists()) dir.mkdirs();

    // 存储分片（文件名：shard-{索引}）
    String shardPath = shardDir + File.separator + "shard-" + shardIndex;
    File shardFile = new File(shardPath);
    shard.transferTo(shardFile);

    return R.ok() ;
  }

  // 2. 查询已上传分片
  @GetMapping("/shards")
  public R getUploadedShards(@RequestParam Map<String, String> params) {
    String fileMd5 = params.get("params[fileMd5]");
    if (fileMd5 == null) {
      return new R().put("uploaded", Collections.emptyList());
    }

    String shardDir = uploadConfig.getShardTempPath(fileMd5);
    File dir = new File(shardDir);
    if (!dir.exists()) return new R().put("uploaded", Collections.emptyList());

    List<Integer> shardIndexes = Arrays.stream(dir.listFiles())
            .filter(file -> file.getName().startsWith("shard-"))
            .map(file -> {
              String name = file.getName();
              return Integer.parseInt(name.split("-")[1]);
            })
            .sorted()
            .collect(Collectors.toList());

    return new R().put("uploaded", shardIndexes);
  }

  // 3. 合并分片
  @PostMapping("/merge")
  public Result<?> mergeShards(@RequestBody MergeDTO mergeDTO) throws IOException {
    String fileMd5 = mergeDTO.getFileMd5();
    String fileName = mergeDTO.getFileName();
    int totalShards = mergeDTO.getTotalShards();

    // 校验所有分片是否齐全
    String shardDir = uploadConfig.getShardTempPath(fileMd5);
    File dir = new File(shardDir);
    File[] shardFiles = dir.listFiles((f) -> f.getName().startsWith("shard-"));
    if (shardFiles == null || shardFiles.length != totalShards) {
      return  new Result<>().error("分片不齐全，请重新上传");
    }

    // 合并分片为完整压缩包
    String completeFilePath = uploadConfig.getCompleteFilePath(fileMd5, fileName);
    File completeFile = new File(completeFilePath);
    try (FileOutputStream out = new FileOutputStream(completeFile)) {
      // 按索引顺序合并
      for (int i = 0; i < totalShards; i++) {
        String shardPath = shardDir + File.separator + "shard-" + i;
        File shardFile = new File(shardPath);
        try (FileInputStream in = new FileInputStream(shardFile)) {
          byte[] buffer = new byte[1024 * 1024]; // 1MB缓冲区
          int len;
          while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
          }
        }
        // 合并后删除分片（可选：节省磁盘空间）
        shardFile.delete();
      }
    }

    // 删除分片目录
    dir.delete();

    // 提交异步任务：解压+解析+入库
    taskStatusMap.put(fileMd5, new TaskStatus("processing", 0));
    uploadTaskExecutor.execute(() -> {
      try {
        // 解压+解析+入库（FileProcessService自定义实现）
        fileProcessService.process(completeFile, fileMd5, taskStatusMap);
        taskStatusMap.put(fileMd5, new TaskStatus("completed", 100));
      } catch (Exception e) {
        log.error("文件处理失败", e);
        taskStatusMap.put(fileMd5, new TaskStatus("failed", 0, e.getMessage()));
      }
    });


    return new Result<>().ok();
  }

  // 4. 查询任务状态（解压+入库进度）
  @GetMapping("/taskStatus")
  public R getTaskStatus(@RequestParam String fileMd5) {
    TaskStatus status = taskStatusMap.getOrDefault(fileMd5, new TaskStatus("not_started", 0));
    return new R().put("status", status);
  }

  // 5. 取消上传（删除已上传分片）
  @DeleteMapping("/cancel")
  public R cancelUpload(@RequestParam String fileMd5) throws IOException {
    String shardDir = uploadConfig.getShardTempPath(fileMd5);
    File dir = new File(shardDir);
    if (dir.exists()) {
      // 递归删除分片目录
      FileUtils.deleteDirectory(dir); // 需要commons-io依赖
    }
    taskStatusMap.remove(fileMd5);
    return  R.ok();
  }


}