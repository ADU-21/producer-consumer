package com.adu21.producerconsumer.service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Slf4j
@RequiredArgsConstructor
public class RecordDistributor {

    private static final int QUEUE_MAX_SIZE = 100_000;

    private final ScheduledExecutorService scheduledExecutorService;
    private final ConcurrentLinkedQueue<String> recordQueue = new ConcurrentLinkedQueue<>();
    private final Semaphore queueItemSemaphore = new Semaphore(0);
    private final AtomicBoolean serviceIsScheduled = new AtomicBoolean();

    public void start() {
        log.info("RecordDistributor start");
        scheduledExecutorService.scheduleWithFixedDelay(this::updateConsumers, 0, 1, TimeUnit.MILLISECONDS);
        serviceIsScheduled.set(true);
    }

    public void stop() {
        log.info("RecordDistributor stop");
        scheduledExecutorService.shutdown();
        serviceIsScheduled.set(false);
    }

    public void enqueue(String query) {

    }

    private void updateConsumers() {
    }
}
