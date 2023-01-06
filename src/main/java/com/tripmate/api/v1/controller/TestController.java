package com.tripmate.api.v1.controller;

import com.tripmate.domain.test.dto.TestDTO;
import com.tripmate.domain.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/test")
public class TestController {
    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("return")
    public TestDTO test() {
        return new TestDTO("test1", "test2", "test3");
    }

    @GetMapping("dao")
    public String testDAO() {
        return testService.test();
    }
}
