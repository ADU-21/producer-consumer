package com.adu21.producerconsumer.metric.concumer;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.adu21.producerconsumer.metric.counter.MetricCounter;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Slf4j
@Builder
public class MetricConsumerImpl implements MetricConsumer {

    private static final int MAX_JITTER_MILLISECONDS = 100;
    private static final int WRITE_INTERVAL_AND_START_MILLISECONDS = 1000;
    private final MetricCounter metricCounter;

    private final ScheduledExecutorService scheduledExecutorService;
    private final AtomicBoolean serviceIsScheduled = new AtomicBoolean();

    @Override
    public boolean consume(List<String> metrics) {
        log.info(metrics.toString());
        if (!serviceIsScheduled.get()) {
            log.error("Failed to consume metrics before consume thread scheduled!");
            return false;
        }
        metricCounter.updateCounts(metrics);
        return false;
    }

    @Override
    public void start() {
        // jitter to avoid hosts in clusters write at same time
        int jitter = new Random().nextInt(MAX_JITTER_MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(this::sendStatistics,
            WRITE_INTERVAL_AND_START_MILLISECONDS + jitter, WRITE_INTERVAL_AND_START_MILLISECONDS + jitter,
            TimeUnit.MILLISECONDS);
        serviceIsScheduled.set(true);
    }

    @Override
    public void stop() {
        scheduledExecutorService.shutdown();
        serviceIsScheduled.set(false);
    }

    private void sendStatistics() {
        // Write metrics statistics to file
        Map<String, Integer> metricStatics = metricCounter.drain();
    }
}
