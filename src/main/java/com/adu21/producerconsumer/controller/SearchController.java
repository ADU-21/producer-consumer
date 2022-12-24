package com.adu21.producerconsumer.controller;

import com.adu21.producerconsumer.service.QueryService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LukeDu
 * @date 2022/12/19
 */
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final QueryService queryService;

    @GetMapping("/search")
    public String search(@RequestParam("q") String q) {
        return queryService.query(q);
    }
}
