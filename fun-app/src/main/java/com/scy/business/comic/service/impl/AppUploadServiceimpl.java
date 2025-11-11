package com.scy.business.comic.service.impl;

import com.scy.business.comic.service.AppUploadServiceA;
import com.scy.comic.service.impl.ComicProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("AppUploadServiceA")
@Slf4j
public class AppUploadServiceimpl implements AppUploadServiceA {

    private final ComicProcessingService comicProcessingService;

    public AppUploadServiceimpl(ComicProcessingService comicProcessingService) {
        this.comicProcessingService = comicProcessingService;
    }

    @Override
    public String uploadComic(MultipartFile file) {
        try {
            return comicProcessingService.processComicZip( file);
        } catch (IOException e) {
            log.error("处理漫画ZIP包时出错: ", e);
            throw new RuntimeException("处理漫画ZIP包失败: " + e.getMessage(), e);
        }
    }
}
