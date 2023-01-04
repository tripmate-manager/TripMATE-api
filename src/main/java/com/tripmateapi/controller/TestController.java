package com.tripmateapi.controller;

import com.tripmateapi.dto.TestDTO;
import com.tripmateapi.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("return")
    public TestDTO test() {
        return new TestDTO("test1", "test2", "test3");
    }

    @GetMapping("dao")
    public String testDAO() {
        return testService.test();
    }
}
