/*
package com.scy.service.impl;


import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.scy.service.GoogleDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@Service
public class GoogleDriveServiceImpl implements GoogleDriveService {

    private final Drive driveService;
    
    @Value("${google.drive.folder-id:}")
    private String defaultFolderId;

    @Autowired
    public GoogleDriveServiceImpl(Drive driveService) {
        this.driveService = driveService;
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        // 如果配置了默认文件夹，则上传到默认文件夹
        if (defaultFolderId != null && !defaultFolderId.isEmpty()) {
            return uploadFileToFolder(file, defaultFolderId);
        }
        return uploadFileToFolder(file, null);
    }

    @Override
    public String uploadFileToFolder(MultipartFile file, String folderId) throws IOException {
        // 创建文件元数据
        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());
        if (folderId != null && !folderId.isEmpty()) {
            fileMetadata.setParents(Collections.singletonList(folderId));
        }

        // 上传文件
        try (InputStream inputStream = file.getInputStream()) {
            File uploadedFile = driveService.files().create(fileMetadata, 
                            new com.google.api.client.http.FileContent(file.getContentType(), 
                                    convertMultipartFileToFile(file)))
                    .setFields("id")
                    .execute();

            // 设置文件权限为公开可查看
            Permission permission = new Permission()
                    .setType("anyone")
                    .setRole("reader");
            driveService.permissions().create(uploadedFile.getId(), permission).execute();

            // 返回文件的共享链接
            return "https://drive.google.com/file/d/" + uploadedFile.getId() + "/view";
        }
    }

    // 辅助方法：将MultipartFile转换为File
    private java.io.File convertMultipartFileToFile(MultipartFile file) throws IOException {
        java.io.File convFile = new java.io.File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
    }
}
*/
