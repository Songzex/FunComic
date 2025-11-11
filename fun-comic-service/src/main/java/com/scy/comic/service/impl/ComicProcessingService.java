package com.scy.comic.service.impl;


import com.scy.modles.comic.AddComic;
import com.scy.comic.pojo.Chapter;
import com.scy.comic.pojo.Comic;
import com.scy.common.mongodb.MongoChapter;
import com.scy.comic.service.ChapterService;
import com.scy.comic.service.ComicService;
import com.scy.utils.QiniuKodoUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author 24022
 */
@Service("ComicProcessingService")
public class ComicProcessingService {

    private final QiniuKodoUtil qiniuKodoUtil;
    private final ComicService comicService;
    private final ChapterService chapterService;
    private final MongoTemplate mongoTemplate;

    public ComicProcessingService( QiniuKodoUtil qiniuKodoUtil, ComicService comicService, ChapterService chapterService, MongoTemplate mongoTemplate) {
        this.qiniuKodoUtil = qiniuKodoUtil;
        this.comicService = comicService;
        this.chapterService = chapterService;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 处理上传的漫画ZIP包
     * @param zipFile ZIP压缩包
     * @return 处理后的漫画对象
     * @throws IOException 处理过程中的IO异常
     */
    public String processComicZip(MultipartFile zipFile) throws IOException {
        // 创建临时目录
        Path tempDir = Files.createTempDirectory("comic-upload-");
        
        try {
            // 解压ZIP文件到临时目录
            unzip(zipFile.getInputStream(), tempDir.toString());
            
            // 获取漫画根目录（ZIP中的第一个目录）
            Path comicRootDir = getComicRootDirectory(tempDir);
            String comicName = comicRootDir.getFileName().toString();
            //入库漫画
            AddComic comic = new AddComic();
            comic.setDescription(comicName);
            Comic comicId = comicService.addComicToId(comic);

            // 解析章节和图片
            List<Chapter> chapters = parseChapters(comicRootDir, comicId);
            
            // 按章节名排序
            chapters.sort(Comparator.comparingInt(Chapter::getId));
            
            // 创建并返回漫画对象
            return "ok";
        } finally {
            // 清理临时文件
            deleteDirectory(tempDir.toFile());
        }
    }

    /**
     * 解压ZIP文件
     */
    private void unzip(InputStream zipInputStream, String destDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(zipInputStream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(destDir, entry.getName());
                
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    // 确保父目录存在
                    new File(file.getParent()).mkdirs();
                    
                    // 写入文件
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }

    /**
     * 获取漫画根目录（ZIP解压后的第一个目录）
     */
    private Path getComicRootDirectory(Path tempDir) throws IOException {
        List<Path> rootEntries = Files.list(tempDir)
                .filter(Files::isDirectory)
                .collect(Collectors.toList());
        
        if (rootEntries.isEmpty()) {
            throw new IOException("ZIP文件中未找到漫画根目录");
        }
        
        // 取第一个目录作为漫画根目录
        return rootEntries.get(0);
    }

    /**
     * 解析章节和图片
     */
    private List<Chapter> parseChapters(Path comicRootDir, Comic comic) throws IOException {
        List<Chapter> chapters = new ArrayList<>();
        
        // 获取所有章节目录并按名称排序
        List<Path> chapterDirs = Files.list(comicRootDir)
                .filter(Files::isDirectory)
                .sorted(Comparator.comparing(path -> path.getFileName().toString()))
                .collect(Collectors.toList());
        
        // 处理每个章节
        int chapterOrder = 1;
        for (Path chapterDir : chapterDirs) {
            //入mysql库 chapter
            Chapter chapter = new Chapter();
            chapter.setComicid(comic.getId());
            chapter.setChapterid(chapterOrder);
            chapter.setIsfree("免费");
            String chapterName = chapterDir.getFileName().toString();
            chapter.setTitle(chapterName);
            Boolean chap =chapterService.addChapters(chapter);
            
            // 获取章节内的所有图片并按名称排序
            List<Path> imageFiles = Files.list(chapterDir)
                    .filter(this::isImageFile)
                    .sorted(Comparator.comparing(path -> path.getFileName().toString()))
                    .collect(Collectors.toList());
            
            // 上传图片并收集URL
            // 上传图片并收集URL
            List<String> imageUrls = new ArrayList<>();
            for (Path imageFile : imageFiles) {
                String fileName = imageFile.getFileName().toString();

                // 创建 MultipartFile 实例
                byte[] fileBytes = Files.readAllBytes(imageFile);
                MultipartFile multipartFile = new MockMultipartFile(
                        fileName,
                        fileName,
                        getContentType(fileName),
                        fileBytes
                );

                // 使用现有的 uploadFile 方法
               // String imageUrl = storageService.uploadFile(multipartFile);
                String imageUrl = qiniuKodoUtil.uploadFile(multipartFile);
                imageUrls.add(imageUrl);

            }
            //入mongodb库 chapter
            MongoChapter chapter1 = new MongoChapter();
            chapter1.setDesc(chapterName);
            chapter1.setComicID(comic.getComicid());
            chapter1.setUrl(imageUrls);
            mongoTemplate.insert(chapter1, "comicCollection");
            // 创建章节对象
          // chapters.add(new Chapter(chapterName, imageUrls, chapterOrder++));
        }
        
        return chapters;
    }



    /**
     * 根据文件扩展名获取内容类型
     */
    private String getContentType(String fileName) {
        String extension = getFileExtension(fileName).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            default:
                return "application/octet-stream";
        }
    }

    /**
     * 获取文件扩展名
     */
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    /**
     * 判断是否为图片文件
     */
    private boolean isImageFile(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        return fileName.endsWith(".jpg") || 
               fileName.endsWith(".jpeg") || 
               fileName.endsWith(".png") || 
               fileName.endsWith(".gif");
    }

    /**
     * 递归删除目录
     */
    private void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }


}
