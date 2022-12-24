package com.adu21.producerconsumer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Component
@RequiredArgsConstructor
public class QueryObserver {
    private final MetricDistributor metricDistributor;

    public void update(String query) {
        metricDistributor.enqueue(query);
    }
}
