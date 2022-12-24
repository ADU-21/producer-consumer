package com.adu21.producerconsumer.config;

import java.util.concurrent.Executors;

import com.adu21.producerconsumer.service.RecordDistributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Configuration
public class AppConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    RecordDistributor recordDistributor() {
        return new RecordDistributor(Executors.newSingleThreadScheduledExecutor());
    }

}
