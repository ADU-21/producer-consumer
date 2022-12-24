package com.adu21.producerconsumer.metric.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Component
@Slf4j
public class StaticsWriter {
    public void write(String line) {
        log.info(line);
    }
}
