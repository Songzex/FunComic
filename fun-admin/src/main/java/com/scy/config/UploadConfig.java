package com.scy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class UploadConfig {
    // 1. 线程池配置（处理解压+入库）
    @Bean
    public ThreadPoolTaskExecutor uploadTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int corePoolSize = Runtime.getRuntime().availableProcessors() * 2; // 核心线程数=CPU核心数*2
        int maxPoolSize = corePoolSize * 2; // 最大线程数=核心线程数*2
        long keepAliveSeconds = 60; // 空闲线程存活时间
        int queueCapacity = 10; // 任务队列大小（避免积压）
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds((int) keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("UploadTask-");
        // 任务拒绝策略：队列满时，由调用线程执行（避免任务丢失）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    // 2. 文件存储配置（临时分片+最终压缩包）
    @Value("${upload.temp-path:D:\\FunComic\\fun-admin\\src\\test}")
    private String tempPath; // 临时分片存储路径
    @Value("${upload.complete-path:D:\\FunComic\\fun-admin\\src\\test\\complete}")
    private String completePath; // 完整压缩包存储路径

    @PostConstruct
    public void initDir() {
        File tempDir = new File(tempPath);
        File completeDir = new File(completePath);
        if (!tempDir.exists()) tempDir.mkdirs();
        if (!completeDir.exists()) completeDir.mkdirs();
    }

    public String getShardTempPath(String fileMd5) {
        return tempPath + File.separator + fileMd5; // 每个文件的分片单独存一个目录
    }

    public String getCompleteFilePath(String fileMd5, String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return completePath + File.separator + fileMd5 + suffix; // 完整文件路径（MD5命名避免重复）
    }
}
