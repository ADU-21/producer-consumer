package com.adu21.producerconsumer.metric;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

import com.adu21.producerconsumer.metric.concumer.MetricConsumer;
import io.micrometer.common.util.StringUtils;
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

    private final List<MetricConsumer> metricConsumers = new LinkedList<>();

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
            log.error("Failed enqueue metric before distributor thread scheduled!");
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

    public void addConsumer(MetricConsumer metricConsumer) {
        metricConsumers.add(metricConsumer);
    }

    /**
     * This method blocks until the number of permits in the semaphore is greater than 0.
     */
    private void updateConsumers() {
        queueItemSemaphore.acquireUninterruptibly();
        int numberOfItemsToPoll = queueItemSemaphore.drainPermits();
        List<String> metrics = IntStream.rangeClosed(0, numberOfItemsToPoll).mapToObj(i -> metricQueue.poll())
            .filter(StringUtils::isNotEmpty).toList();
        metricConsumers.forEach(consumer -> consumer.consume(metrics));
    }
}
