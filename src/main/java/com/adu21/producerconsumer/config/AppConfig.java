package com.adu21.producerconsumer.config;

import java.util.concurrent.Executors;

import com.adu21.producerconsumer.service.MetricConsumer;
import com.adu21.producerconsumer.service.MetricConsumerImpl;
import com.adu21.producerconsumer.service.MetricDistributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Configuration
public class AppConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    MetricDistributor metricDistributor(MetricConsumer metricConsumer) {
        MetricDistributor metricDistributor = new MetricDistributor(Executors.newSingleThreadScheduledExecutor());
        metricDistributor.addConsumer(metricConsumer);
        return metricDistributor;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    MetricConsumer metricConsumer() {
        return new MetricConsumerImpl();
    }

}
