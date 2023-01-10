package com.tripmate.api.v1.controller;

import com.tripmate.domain.common.dto.MailDTO;
import com.tripmate.domain.common.service.MailService;
import com.tripmate.domain.common.vo.ListResponse;
import com.tripmate.domain.common.vo.ObjectResponse;
import com.tripmate.domain.test.dto.TestDTO;
import com.tripmate.domain.test.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "테스트 API", description = "Test API")
@RequestMapping("/v1/test")
public class TestController {
    private TestService testService;
    private MailService mailService;

    @Autowired
    public TestController(TestService testService, MailService mailService) {
        this.testService = testService;
        this.mailService = mailService;
    }

    @Operation(summary = "json return", description = "JSON 형식 반환 예시")
    @GetMapping("return")
    public ListResponse<TestDTO> test() {
        List<TestDTO> data = new ArrayList<>();
        data.add(new TestDTO("test1", "test2", "test3"));
        data.add(new TestDTO("test11", "test22", "test33"));
        data.add(new TestDTO("test111", "test222", "test333"));

        return ListResponse.<TestDTO>builder()
                           .data(data)
                           .build();
    }

    @Operation(summary = "db 쿼리", description = "DB 쿼리 수행 후 반환 예시")
    @GetMapping("dao")
    public String testDAO() {
        return testService.test();
    }

    @Operation(summary = "메일전송", description = "메일전송 예시")
    @PostMapping("sendMail")
    public ObjectResponse<String> sendMail(@Valid @RequestBody MailDTO mail) {
        try {
            mailService.sendMail(mail);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }

        return ObjectResponse.<String>builder()
                             .data("--DATA TEST--")
                             .build();
    }
}
