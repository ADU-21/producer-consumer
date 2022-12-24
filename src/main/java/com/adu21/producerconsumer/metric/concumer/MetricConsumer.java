package com.adu21.producerconsumer.metric.concumer;

import java.util.List;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
public interface MetricConsumer {
    boolean consume(List<String> metrics);

    void start();

    void stop();
}
