package com.adu21.producerconsumer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Component
@RequiredArgsConstructor
public class QueryObserver implements MetricObserver {

    private static final String QUERY_METRIC_PREFIX = "Query:";
    private final MetricDistributor metricDistributor;

    @Override
    public void update(String query) {
        metricDistributor.enqueue(QUERY_METRIC_PREFIX + query);
    }
}
