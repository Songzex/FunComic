package com.scy.utils;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

@Component
@Slf4j
public class MinioUploadUtils {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.bucket}")
    private String bucketName;

    public String upload(MultipartFile file) {
        try {
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            // 生成唯一文件名
            String uniqueFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFilename;

            // 使用 try-with-resources 确保 InputStream 正确关闭
            try (InputStream inputStream = file.getInputStream()) {
                PutObjectArgs args = PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(uniqueFileName)
                        .stream(inputStream, file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build();

                minioClient.putObject(args);
            }

            // 返回文件访问URL
            return getFileUrls(uniqueFileName);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return null;
        }
    }

    public String upload(String localFilePath) {
        try {
            // 获取文件名
            String fileName = localFilePath.substring(localFilePath.lastIndexOf("/") + 1);
            // 生成唯一文件名
            String uniqueFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;

            // 使用 try-with-resources 确保 FileInputStream 正确关闭
            try (FileInputStream fileInputStream = new FileInputStream(localFilePath)) {
                PutObjectArgs args = PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(uniqueFileName)
                        .stream(fileInputStream, new File(localFilePath).length(), -1)
                        .build();

                minioClient.putObject(args);
            }

            // 返回文件访问URL
            return getFileUrls(uniqueFileName);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return null;
        }
    }

    private String getFileUrls(String fileName) {
        log.info("获取文件访问URL");
        // 拼接 http://oos.muaiweiyi.com/bucketName/fileName
        return "http://oos.muaiweiyi.com" + "/" + bucketName + "/" + fileName;
    }
}
