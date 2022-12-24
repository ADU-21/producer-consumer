package com.adu21.producerconsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author LukeDu
 * @date 2022/12/24
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class QueryService {
    private final MetricObserver metricObserver;

    public String query(String query) {
        //log.info(query);
        metricObserver.update(query);
        return query;
    }
}
