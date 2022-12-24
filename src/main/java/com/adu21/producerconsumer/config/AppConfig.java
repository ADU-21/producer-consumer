package com.adu21.producerconsumer.config;

import java.util.concurrent.Executors;

import com.adu21.producerconsumer.metric.MetricDistributor;
import com.adu21.producerconsumer.metric.concumer.MetricConsumer;
import com.adu21.producerconsumer.metric.concumer.MetricConsumerImpl;
import com.adu21.producerconsumer.metric.counter.MetricCounter;
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
    MetricConsumer metricConsumer(MetricCounter metricCounter) {
        return MetricConsumerImpl.builder()
            .scheduledExecutorService(Executors.newSingleThreadScheduledExecutor())
            .metricCounter(metricCounter)
            .build();
    }

}
