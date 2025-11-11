package com.scy.business.fileupload.service.impl;

import com.scy.business.fileupload.service.AppUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("AppUploadService")
public class AppUploadServiceimpl implements AppUploadService {
  /*private  final   GoogleDriveService googleDriveService;

    public AppUploadServiceimpl(GoogleDriveService googleDriveService) {
        this.googleDriveService = googleDriveService;
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        return googleDriveService.uploadFile( file);
    }*/

//    @Override
//    public String uploadFileToFolder(MultipartFile file, String folderId) throws IOException {
//        return googleDriveService.uploadFileToFolder(file, folderId);
//    }
}
