package com.tripmate.api.v1.controller;

import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.member.dto.MemberDTO;
import com.tripmate.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "회원 API", description = "Member API")
@RequestMapping("v1/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "회원가입", description = "회원 가입")
    @PostMapping
    public void signUp(@Valid @RequestBody MemberDTO memberDTO) {
        memberService.signUp(memberDTO);
    }

    @Operation(summary = "아이디 중복 조회", description = "memberId 기준 MB_MBR_MGMT 테이블에서 회원 카운트 조회")
    @GetMapping("/idCheck/{memberId}")
    public ResponseWrapper<Integer> getMemberIdCount(@PathVariable(value = "memberId") @Schema(example = "testid") String memberId) {
        return ResponseWrapper.<Integer>builder()
                .data(Collections.singletonList(memberService.getMemberIdCount(memberId)))
                .build();
    }

    @Operation(summary = "닉네임 중복 조회", description = "memberNickName 기준 MB_MBR_MGMT 테이블에서 회원 카운트 조회")
    @GetMapping("/nickNameCheck/{memberNickName}")
    public ResponseWrapper<Integer> getMemberNickNameCount(@PathVariable(value = "memberNickName") @Schema(example = "닉네임") String memberNickName) {
        return ResponseWrapper.<Integer>builder()
                .data(Collections.singletonList(memberService.getMemberNickNameCount(memberNickName)))
                .build();
    }

    @Operation(summary = "이메일 중복 조회", description = "memberEmail 기준 MB_MBR_MGMT 테이블에서 회원 카운트 조회")
    @GetMapping("/emailCheck/{memberEmail}")
    public ResponseWrapper<Integer> getMemberEmailCount(@PathVariable(value = "memberEmail") @Schema(example = "test@test.com") String memberEmail) {
        return ResponseWrapper.<Integer>builder()
                .data(Collections.singletonList(memberService.getMemberEmailCount(memberEmail)))
                .build();
    }
}

