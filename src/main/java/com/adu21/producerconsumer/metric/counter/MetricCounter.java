package com.adu21.producerconsumer.metric.counter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Component
public class MetricCounter {
    public synchronized void updateCounts(List<String> metrics) {
        // update metric counts
    }

    public synchronized Map<String, Integer> drain() {
        return null;
    }
}
