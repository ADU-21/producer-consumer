package com.adu21.producerconsumer.metric.writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * @author LukeDu
 * @date 2022/12/24
 */
@Component
@Slf4j
public class StaticsWriter {
    private static final String METRIC_STATISTICS_FILE = "metrics.txt";

    public void write(String line) {
        log.info(line);
        line += System.lineSeparator();
        byte[] bytes = line.getBytes();
        Path path = Paths.get(METRIC_STATISTICS_FILE);

        try {
            Files.write(path, bytes, APPEND, CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
