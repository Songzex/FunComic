package com.scy.common.config;


import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 24022
 */
@Configuration
public class MInIoConfig {
    @Value("${minio.endPoint}")
    public String endPoint;
    @Value("${minio.accessKey}")
    public String accessKey;
    @Value("${minio.secretKey}")
    public String secretKey;

    /*
    * 创建实例
    * */
    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(endPoint)
                .credentials(accessKey, secretKey)
                .build();
    }

}
