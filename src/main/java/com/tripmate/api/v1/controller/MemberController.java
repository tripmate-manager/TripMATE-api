package com.tripmate.api.v1.controller;

import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.common.dto.MailDTO;
import com.tripmate.domain.common.service.MailService;
import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.member.dto.DuplicationCheckDTO;
import com.tripmate.domain.member.dto.MemberDTO;
import com.tripmate.domain.member.dto.MemberMailDTO;
import com.tripmate.domain.member.dto.SignInDTO;
import com.tripmate.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "회원 API", description = "Member API")
@RequestMapping("v1/members")
public class MemberController {
    private final MemberService memberService;
    private final MailService mailService;

    @Autowired
    public MemberController(MemberService memberService, MailService mailService) {
        this.memberService = memberService;
        this.mailService = mailService;
    }

    @Operation(summary = "회원가입", description = "회원 가입 및 인증 메일 전송 (return: 회원 번호)")
    @PostMapping
    public ResponseWrapper<Integer> signUp(@Valid @RequestBody MemberDTO memberDTO) {
        int signUpResult = memberService.signUp(memberDTO);

        if (signUpResult > 0) {
            MailDTO mailDTO = MailDTO.builder()
                    .to(memberDTO.getEmail())
                    .build();
            try {
                mailService.sendSignUpMail(mailDTO);
            } catch (MessagingException e) {
                log.error(e.getMessage(), e);
            }
        }

        return ResponseWrapper.<Integer>builder()
                .data(Collections.singletonList(signUpResult))
                .build();
    }

    @Operation(summary = "아이디 중복 조회", description = "이미 사용중인 아이디인지 중복여부를 체크합니다. (true: 사용 가능한 아이디 / false: 중복된 아이디)")
    @GetMapping("duplication/id")
    public ResponseWrapper<Boolean> isMemberIdDuplicate(@RequestParam(value = "memberId") @Schema(example = "회원ID") String memberId) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(isDuplicate(memberId, ConstCode.DUPLICATION_CHECK_MEMBER_ID)))
                .build();
    }

    @Operation(summary = "닉네임 중복 조회", description = "이미 사용중인 닉네임인지 중복여부를 체크합니다. (true: 사용 가능한 닉네임 / false: 중복된 닉네임)")
    @GetMapping("duplication/nick-name")
    public ResponseWrapper<Boolean> isNickNameDuplicate(@RequestParam(value = "nickName") @Schema(example = "닉네임") String nickName) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(isDuplicate(nickName, ConstCode.DUPLICATION_CHECK_NICK_NAME)))
                .build();
    }

    @Operation(summary = "이메일 중복 조회", description = "이미 사용중인 이메일인지 중복여부를 체크합니다. (true: 사용 가능한 이메일 / false: 중복된 이메일)")
    @GetMapping("duplication/email")
    public ResponseWrapper<Boolean> isEmailDuplicate(@RequestParam(value = "email") @Schema(example = "test@test.com") String email) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(isDuplicate(email, ConstCode.DUPLICATION_CHECK_EMAIL)))
                .build();
    }

    private boolean isDuplicate(String value, String type) {
        return memberService.isDuplicate(DuplicationCheckDTO.builder()
                                                            .duplicationMemberInfo(value)
                                                            .duplicationCheckType(type)
                                                            .build());
    }

    @Operation(summary = "회원가입 인증메일 확인", description = "회원가입 인증메일을 처리합니다. (true: 인증완료 / false: 미인증처리)")
    @GetMapping("signup-mail-confirm")
    public ResponseWrapper signUpMailConfirm(@RequestParam(value = "email") @Schema(example = "test@test.com") @NonNull @Email String email,
                                                      @RequestParam(value = "key") @Schema(example = "인증키") @NonNull @Size(max = 100) String key) {
        memberService.signUpMailConfirm(MemberMailDTO.builder()
                .email(email)
                .key(key)
                .mailTypeCode(ConstCode.EMAIL_TYPE_CODE_JOIN)
                .build());

        return ResponseWrapper.builder().build();
    }

    @Operation(summary = "로그인 요청", description = "로그인 처리합니다. (return: 회원 정보)")
    @PostMapping("signIn")
    public ResponseWrapper<MemberDTO> signIn(@Valid @RequestBody SignInDTO signInDTO) {
        return ResponseWrapper.<MemberDTO>builder()
                .data(Collections.singletonList(memberService.signIn(signInDTO)))
                .build();
    }
}

