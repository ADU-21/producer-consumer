package com.adu21.producerconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author LukeDu
 * @date 2022/12/24
 */

@Service
@Slf4j
public class QueryService {
    public String query(String query) {
        log.info(query);
        return query;
    }
}
