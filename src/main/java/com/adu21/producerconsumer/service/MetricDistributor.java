package com.adu21.producerconsumer.service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Slf4j
@RequiredArgsConstructor
public class MetricDistributor {

    /**
     * The QUEUE_MAX_SIZE limit is introduced in an effort to consume memory in a safer manner.
     * The metric will be ignored if number of items in the metricQueue exceeds this limit.
     */
    private static final int QUEUE_MAX_SIZE = 100_000;

    private final ScheduledExecutorService scheduledExecutorService;
    private final AtomicBoolean serviceIsScheduled = new AtomicBoolean();

    // queue stores metrics, non-blocking
    private final ConcurrentLinkedQueue<String> metricQueue = new ConcurrentLinkedQueue<>();
    // counter of metrics, avoid call O(N) linkedQueue.size()
    private final Semaphore queueItemSemaphore = new Semaphore(0);

    public void start() {
        log.info("MetricDistributor start");
        // check if update consumers needed very 1ms
        scheduledExecutorService.scheduleWithFixedDelay(this::updateConsumers, 0, 1, TimeUnit.MILLISECONDS);
        serviceIsScheduled.set(true);
    }

    public void stop() {
        log.info("MetricDistributor stop");
        scheduledExecutorService.shutdown();
        serviceIsScheduled.set(false);
    }

    public boolean enqueue(String query) {
        if (!serviceIsScheduled.get()) {
            log.error("Invocation of enqueue occurred before MetricDistributor was scheduled");
            return false;
        }

        if (queueItemSemaphore.availablePermits() < QUEUE_MAX_SIZE) {
            metricQueue.offer(query);
            queueItemSemaphore.release();
            return true;
        }

        log.warn("MetricQueue was full!");
        return false;
    }

    private void updateConsumers() {
        // update metrics in consumers
    }
}
