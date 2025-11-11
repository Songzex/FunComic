package com.scy.business.fileupload;


import com.scy.api.R;
import com.scy.business.fileupload.service.AppUploadService;
import com.scy.utils.MinioUploadUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final MinioUploadUtils minioUploadUtils;

    @Autowired
    public FileUploadController(MinioUploadUtils minioUploadUtils) {
        this.minioUploadUtils = minioUploadUtils;
    }

//    /**
//     * 上传文件到默认文件夹
//     */
//    @PostMapping("/upload")
//    @Operation(
//            summary = "上传返回外连",
//            description = "用户上传接口"
//    )
//    public R uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            String fileUrl = googleDriveService.uploadFile(file);
//            Map<String, String> response = new HashMap<>();
//            response.put("message", "File uploaded successfully");
//            response.put("fileUrl", fileUrl);
//            int start = fileUrl.indexOf("/d/") + 3;
//            int end = fileUrl.indexOf("/view");
//            String fileId = fileUrl.substring(start, end);
//            return  R.ok(fileId);
//        } catch (IOException e) {
//            Map<String, String> error = new HashMap<>();
//            error.put("error", "Failed to upload file: " + e.getMessage());
//            return  R.error("上传失败");
//        }
//    }

//    /**
//     * 上传文件到指定文件夹
//     */
//    @PostMapping("/upload-to-folder")
//    public ResponseEntity<Map<String, String>> uploadFileToFolder(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("folderId") String folderId) {
//        try {
//            String fileUrl = googleDriveService.uploadFileToFolder(file, folderId);
//            Map<String, String> response = new HashMap<>();
//            response.put("message", "File uploaded to folder successfully");
//            response.put("fileUrl", fileUrl);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (IOException e) {
//            Map<String, String> error = new HashMap<>();
//            error.put("error", "Failed to upload file: " + e.getMessage());
//            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    /*
    * 上传minio 文件
    * */


    @PostMapping("/upload-minio")
    @Operation(
            summary = "上传返回外连",
            description = "上传minio 文件"
    )
    public R uploadFileByMinio(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = minioUploadUtils.upload(file);
            if (fileUrl != null) {
                return R.ok(fileUrl);
            } else {
                return R.error("上传失败");
            }
        } catch (Exception e) {
            return R.error("上传失败: " + e.getMessage());
        }
    }

}