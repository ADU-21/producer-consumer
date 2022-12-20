package com.adu21.producerconsumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LukeDu
 * @date 2022/12/19
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "hello, world!";
    }

}
