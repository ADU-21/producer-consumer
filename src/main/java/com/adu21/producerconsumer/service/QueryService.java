package com.adu21.producerconsumer.service;

import lombok.Builder;
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

    private final QueryObserver queryObserver;

    public String query(String query) {
        log.info(query);
        queryObserver.update(query);
        return query;
    }
}
