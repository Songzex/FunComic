package com.scy.file.service;

import com.scy.modles.update.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;


public interface SysFileProcessService {

    public void process(File zipFile, String fileMd5, ConcurrentHashMap<String, TaskStatus> taskStatusMap) throws Exception;

}
