package com.scy.business.fileupload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AppUploadService  {
    /**
     * 上传文件到谷歌硬盘
     * @param file 要上传的文件
     * @return 文件的共享链接
     */
    //String uploadFile(MultipartFile file) throws IOException;

    /**
     * 上传文件到指定文件夹
     * @param file 要上传的文件
     * @param folderId 目标文件夹ID
     * @return 文件的共享链接
     */
  //  String uploadFileToFolder(MultipartFile file, String folderId) throws IOException;
}
