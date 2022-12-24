package com.adu21.producerconsumer.service;

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
