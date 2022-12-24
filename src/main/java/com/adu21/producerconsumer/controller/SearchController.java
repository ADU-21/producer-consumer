package com.adu21.producerconsumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LukeDu
 * @date 2022/12/19
 */
@Slf4j
@RestController
public class SearchController {

    @GetMapping("/search")
    public String search(@RequestParam("q") String q) {
        log.info(q);
        return q;
    }
}
