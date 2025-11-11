package com.scy.file.service.impl;

import com.scy.file.service.SysFileProcessService;
import com.scy.modles.update.TaskStatus;
import com.scy.uploads.service.impl.FileProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

@Service("SysFileProcessService")
public class SysFileProcessServiceImpl  implements SysFileProcessService {

//    private  final FileProcessService fileProcessService;
//
//    public SysFileProcessServiceImpl(FileProcessService fileProcessService) {
//        this.fileProcessService = fileProcessService;
//    }

    @Autowired
    private   FileProcessService fileProcessService;

    @Override
    public void process(File zipFile, String fileMd5, ConcurrentHashMap<String, TaskStatus> taskStatusMap) throws Exception {
        fileProcessService.process(zipFile, fileMd5, taskStatusMap);
    }
}
