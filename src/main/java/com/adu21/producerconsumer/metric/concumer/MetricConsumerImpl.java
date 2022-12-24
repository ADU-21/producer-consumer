package com.adu21.producerconsumer.metric.concumer;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Slf4j
public class MetricConsumerImpl implements MetricConsumer {
    @Override
    public boolean consume(List<String> metrics) {
        log.info(metrics.toString());
        return false;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
