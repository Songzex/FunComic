package com.scy.uploads.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.scy.modles.update.TaskStatus;
import com.scy.uploads.service.UserUploadService;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class FileProcessService implements UserUploadService {
//  @Autowired
//  private DocumentMapper documentMapper; // MyBatis Mapper：入库用

  // 解压+解析+入库（同步方法，由线程池调用）
  public void process(File zipFile, String fileMd5, ConcurrentHashMap<String, TaskStatus> taskStatusMap) throws Exception {
    // 1. 解压压缩包（临时解压目录）
    String unzipDir = "/data/upload/unzip/" + fileMd5;
    File unzipDirFile = new File(unzipDir);
    if (!unzipDirFile.exists()) unzipDirFile.mkdirs();
    unzip(zipFile, unzipDir);
    taskStatusMap.get(fileMd5).setProgress(30); // 解压完成，进度30%

    // 2. 遍历解压后的文件，按类型解析
    File[] files = unzipDirFile.listFiles((dir, name) -> {
      String lowerName = name.toLowerCase();
      // 只处理doc/docx、excel/xlsx、pdf
      return lowerName.endsWith(".doc") || lowerName.endsWith(".docx")
          || lowerName.endsWith(".xls") || lowerName.endsWith(".xlsx")
          || lowerName.endsWith(".pdf");
    });

    if (files == null || files.length == 0) {
      throw new RuntimeException("压缩包中无有效文件（支持doc/excel/pdf）");
    }

    // 3. 批量解析+入库（按文件类型调用不同解析方法）
    int totalFiles = files.length;
    int processedCount = 0;
    for (File file : files) {
      String fileName = file.getName();
      String content = "";
      if (fileName.toLowerCase().endsWith(".pdf")) {
        content = parsePdf(file);
      } else if (fileName.toLowerCase().matches("\\.doc[x]?")) {
        content = parseDoc(file);
      } else if (fileName.toLowerCase().matches("\\.xls[x]?")) {
        content = parseExcel(file);
      }

      // 入库
//      DocumentPO po = new DocumentPO();
//      po.setFileMd5(fileMd5);
//      po.setFileName(fileName);
//      po.setFileSize(file.length());
//      po.setContent(content);
//      po.setCreateTime(new Date());
//      documentMapper.insert(po);

      // 更新进度
      processedCount++;
      int progress = 30 + (processedCount * 70) / totalFiles; // 30%（解压） + 70%（解析入库）
      taskStatusMap.get(fileMd5).setProgress(progress);
    }

    // 4. 清理临时解压目录（可选）
    FileUtils.deleteDirectory(unzipDirFile);
  }

  // 解压压缩包（支持zip/rar）
  private void unzip(File zipFile, String destDir) throws IOException {
    try (ArchiveInputStream ais = new ArchiveStreamFactory().createArchiveInputStream(
        new BufferedInputStream(new FileInputStream(zipFile)))) {
      ArchiveEntry entry;
      while ((entry = ais.getNextEntry()) != null) {
        if (!ais.canReadEntryData(entry)) continue;
        File entryFile = new File(destDir, entry.getName());
        File parentDir = entryFile.getParentFile();
        if (!parentDir.exists()) parentDir.mkdirs();
        if (entry.isDirectory()) {
          entryFile.mkdirs();
        } else {
          try (FileOutputStream out = new FileOutputStream(entryFile)) {
            byte[] buffer = new byte[1024 * 1024];
            int len;
            while ((len = ais.read(buffer)) != -1) {
              out.write(buffer, 0, len);
            }
          }
        }
      }
    } catch (ArchiveException e) {
        throw new RuntimeException(e);
    }
  }

  // 解析PDF（Apache PDFBox）
  private String parsePdf(File file) throws IOException {
    try (PDDocument document = PDDocument.load(file)) {
      PDFTextStripper stripper = new PDFTextStripper();
      return stripper.getText(document);
    }
  }

  // 解析Doc/Docx（Apache POI）
  private String parseDoc(File file) throws IOException {
    String fileName = file.getName();
    if (fileName.endsWith(".docx")) {
      try (XWPFDocument doc = new XWPFDocument(new FileInputStream(file))) {
        XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
        return extractor.getText();
      }
    } else { // .doc
      try (HWPFDocument doc = new HWPFDocument(new FileInputStream(file))) {
        WordExtractor extractor = new WordExtractor(doc);
        return extractor.getText();
      }
    }
  }

  // 解析Excel（EasyExcel）
  private String parseExcel(File file) {
    List<String> contentList = new ArrayList<>();
    String fileName = file.getName();
    if (fileName.endsWith(".xlsx")) {
      EasyExcel.read(file, new AnalysisEventListener<Map<Integer, String>>() {
        @Override
        public void invoke(Map<Integer, String> data, AnalysisContext context) {
          contentList.add(data.values().stream().collect(Collectors.joining(",")));
        }
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {}
      }).sheet().doRead();
    } else { // .xls
      EasyExcel.read(file, new AnalysisEventListener<Map<Integer, String>>() {
        @Override
        public void invoke(Map<Integer, String> data, AnalysisContext context) {
          contentList.add(data.values().stream().collect(Collectors.joining(",")));
        }
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {}
      }).excelType(ExcelTypeEnum.XLS).sheet().doRead();
    }
    return String.join("\n", contentList);
  }
}