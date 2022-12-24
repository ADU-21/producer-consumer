package com.adu21.producerconsumer.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Component
@RequiredArgsConstructor
public class QueryObserver {

    private final RecordDistributor recordDistributor;

    public void update(String query) {
        recordDistributor.enqueue(query);
    }
}
