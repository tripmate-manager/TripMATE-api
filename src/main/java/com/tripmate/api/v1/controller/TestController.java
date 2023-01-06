package com.tripmate.api.v1.controller;

import com.tripmate.domain.test.dto.TestDTO;
import com.tripmate.domain.test.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "테스트 API", description = "Test API")
@RequestMapping("/v1/test")
public class TestController {
    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Operation(summary = "json return", description = "JSON 형식 반환 예시")
    @GetMapping("return")
    public TestDTO test() {
        return new TestDTO("test1", "test2", "test3");
    }

    @Operation(summary = "db 쿼리", description = "DB 쿼리 수행 후 반환 예시")
    @GetMapping("dao")
    public String testDAO() {
        return testService.test();
    }
}
