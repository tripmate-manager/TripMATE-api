package com.tripmate.api.v1.controller;

import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.member.dto.DuplicationCheckDTO;
import com.tripmate.domain.member.dto.MemberDTO;
import com.tripmate.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Operation(summary = "회원가입", description = "회원 가입 (회원 번호 반환)")
    @PostMapping
    public ResponseWrapper<Integer> signUp(@Valid @RequestBody MemberDTO memberDTO) {
        return ResponseWrapper.<Integer>builder()
                .data(Collections.singletonList(memberService.signUp(memberDTO)))
                .build();
    }

    @Operation(summary = "아이디 중복 조회", description = "이미 사용중인 아이디인지 중복여부를 체크합니다. (true: 사용 가능한 아이디 / false: 중복된 아이디)")
    @GetMapping("/duplication/memberId")
    public ResponseWrapper<Boolean> isMemberIdDuplicate(@RequestParam(value = "memberId") @Schema(example = "회원ID") String memberId) {
        boolean duplication = memberService.isDuplicate(DuplicationCheckDTO.builder()
                .duplicationMemberInfo(memberId)
                .duplicationCheckType(ConstCode.DUPLICATION_CHECK_MEMBER_ID)
                .build());

        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(duplication))
                .build();
    }

    @Operation(summary = "닉네임 중복 조회", description = "이미 사용중인 닉네임인지 중복여부를 체크합니다. (true: 사용 가능한 닉네임 / false: 중복된 닉네임)")
    @GetMapping("/duplication/nickName")
    public ResponseWrapper<Boolean> isNickNameDuplicate(@RequestParam(value = "nickName") @Schema(example = "닉네임") String nickName) {
        boolean duplication = memberService.isDuplicate(DuplicationCheckDTO.builder()
                .duplicationMemberInfo(nickName)
                .duplicationCheckType(ConstCode.DUPLICATION_CHECK_NICK_NAME)
                .build());

        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(duplication))
                .build();
    }

    @Operation(summary = "이메일 중복 조회", description = "이미 사용중인 이메일인지 중복여부를 체크합니다. (true: 사용 가능한 이메일 / false: 중복된 이메일)")
    @GetMapping("/duplication/email")
    public ResponseWrapper<Boolean> isEmailDuplicate(@RequestParam(value = "email") @Schema(example = "test@test.com") String email) {
        boolean duplication = memberService.isDuplicate(DuplicationCheckDTO.builder()
                .duplicationMemberInfo(email)
                .duplicationCheckType(ConstCode.DUPLICATION_CHECK_EMAIL)
                .build());

        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(duplication))
                .build();
    }
}

