package com.adu21.producerconsumer.metric;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import com.adu21.producerconsumer.metric.concumer.MetricConsumer;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MetricDistributorTest {
    @Test
    void testSchedulingInvokesUpdateConsumers() throws InterruptedException {
        // given
        MetricConsumer mockConsumer0 = mock(MetricConsumer.class);
        MetricConsumer mockConsumer1 = mock(MetricConsumer.class);
        MetricDistributor distributor = new MetricDistributor(Executors.newScheduledThreadPool(1));
        distributor.addConsumer(mockConsumer0);
        distributor.addConsumer(mockConsumer1);

        CountDownLatch latch = new CountDownLatch(1);
        doAnswer(invocation -> {
            latch.countDown();
            return null;
        }).when(mockConsumer1).consume(anyList());

        // when
        distributor.start();
        distributor.enqueue("dummyQuery");
        latch.await();
        distributor.stop();

        // then
        verify(mockConsumer0).consume(anyList());
        verify(mockConsumer1).consume(anyList());
    }
}