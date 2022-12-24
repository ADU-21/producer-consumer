package com.adu21.producerconsumer.metric.counter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Component
public class MetricCounter {

    private final Map<String, Integer> metricsCounterMap = new HashMap<>();

    public synchronized void updateCounts(List<String> metrics) {
        for (String metric : metrics) {
            metricsCounterMap.merge(metric, 1, Integer::sum);
        }
    }

    public synchronized Map<String, Integer> drain() {
        Map<String, Integer> countMap = new HashMap<>(metricsCounterMap);
        metricsCounterMap.clear();
        return countMap;
    }
}
