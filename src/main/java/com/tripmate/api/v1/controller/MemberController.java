package com.tripmate.api.v1.controller;

import com.tripmate.common.config.ValidationSequence;
import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.common.service.MailService;
import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.members.dto.DuplicationCheckDTO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import com.tripmate.domain.members.dto.MypageDTO;
import com.tripmate.domain.members.dto.SignInDTO;
import com.tripmate.domain.members.dto.UpdatePasswordDTO;
import com.tripmate.domain.members.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "Member", description = "회원 API")
@RequestMapping("v1/members")
@Validated
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MailService mailService;

    @Operation(summary = "회원가입", description = "회원 가입 및 인증 메일 전송 (return: 회원 번호)")
    @PostMapping
    public ResponseWrapper<Integer> signUp(@Validated(ValidationSequence.class) @RequestBody MemberDTO memberDTO) {
        int signUpResult = memberService.signUp(memberDTO);

        if (signUpResult > 0) {
            MemberMailDTO memberMailDTO = MemberMailDTO.builder()
                    .to(memberDTO.getEmail())
                    .memberId(memberDTO.getMemberId())
                    .mailTypeCode(ConstCode.EMAIL_TYPE_CODE_JOIN)
                    .build();
            try {
                sendCertificationMail(memberMailDTO);
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
    public ResponseWrapper<Boolean> isMemberIdDuplicate(@RequestParam(value = "memberId") @Schema(description = "회원 아이디", example = "testId") @NotBlank @Size(min = 5, max = 20) String memberId) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(isDuplicate(memberId, ConstCode.DUPLICATION_CHECK_MEMBER_ID)))
                .build();
    }

    @Operation(summary = "닉네임 중복 조회", description = "이미 사용중인 닉네임인지 중복여부를 체크합니다. (true: 사용 가능한 닉네임 / false: 중복된 닉네임)")
    @GetMapping("duplication/nick-name")
    public ResponseWrapper<Boolean> isNickNameDuplicate(@RequestParam(value = "nickName") @Schema(description = "닉네임", example = "nickName") @NotBlank @Size(max = 20) String nickName) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(isDuplicate(nickName, ConstCode.DUPLICATION_CHECK_NICK_NAME)))
                .build();
    }

    @Operation(summary = "이메일 중복 조회", description = "이미 사용중인 이메일인지 중복여부를 체크합니다. (true: 사용 가능한 이메일 / false: 중복된 이메일)")
    @GetMapping("duplication/email")
    public ResponseWrapper<Boolean> isEmailDuplicate(@RequestParam(value = "email") @Schema(description = "이메일", example = "test@test.com") @NotBlank @Email String email) {
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

    @Operation(summary = "인증메일 확인", description = "메일 인증을 처리합니다. (true: 인증완료 / false: 미인증처리)")
    @GetMapping("certification-mail-confirm")
    public ResponseWrapper<String> signUpMailConfirm(@RequestParam(value = "memberId") @Schema(description = "회원 아이디", example = "testId") @NotBlank @Size(min = 5, max = 20) String memberId,
                                                     @RequestParam(value = "key") @Schema(description = "인증키") @NotBlank @Size(max = 100) String key,
                                                     @RequestParam(value = "mailTypeCode") @Schema(description = "메일 타입 코드(10: 회원가입, 20: 이메일변경, 30: 임시비밀번호발급)", example = "10") @NotBlank @Pattern(regexp = "^[123]0$") String mailTypeCode) {

        return ResponseWrapper.<String>builder()
                .data(Collections.singletonList(memberService.signUpMailConfirm(MemberMailDTO.builder()
                        .memberId(memberId)
                        .key(key)
                        .mailTypeCode(mailTypeCode)
                        .build())))
                .build();
    }

    @Operation(summary = "로그인 요청", description = "로그인 처리합니다. (return: 회원 정보)")
    @PostMapping("sign-in")
    public ResponseWrapper<MemberDTO> signIn(@Validated(ValidationSequence.class) @RequestBody SignInDTO signInDTO) {
        return ResponseWrapper.<MemberDTO>builder()
                .data(Collections.singletonList(memberService.signIn(signInDTO)))
                .build();
    }

    @Operation(summary = "아이디찾기", description = "입력한 이름과 이메일에 부합하는 아이디를 찾습니다. (return: 아이디)")
    @GetMapping("find-id")
    public ResponseWrapper<String> findId(@RequestParam(value = "memberName") @NotBlank @Size(min = 2, max = 20) @Schema(description = "회원 이름", example = "홍길동") String memberName,
                                          @RequestParam(value = "email") @NotBlank @Email @Schema(description = "email", example = "test@test.com") String email) {
        return ResponseWrapper.<String>builder()
                .data(Collections.singletonList(memberService.findId(MemberDTO.builder()
                        .memberName(memberName)
                        .email(email)
                        .build())))
                .build();
    }

    @Operation(summary = "인증메일 전송", description = "인증 메일을 전송합니다.")
    @PostMapping("send-mail/certification")
    public ResponseWrapper<Boolean> sendCertificationMail(@Validated(ValidationSequence.class) @RequestBody MemberMailDTO memberMailDTO) throws MessagingException {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(mailService.sendCertificationMail(memberMailDTO)))
                .build();
    }

    @Operation(summary = "임시비밀번호 메일 전송", description = "임시 비밀번호를 메일로 전송합니다.")
    @PostMapping("send-mail/password")
    public ResponseWrapper<Boolean> sendPasswordMail(@Validated(ValidationSequence.class) @RequestBody MemberMailDTO memberMailDTO) throws MessagingException {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(mailService.sendPasswordMail(memberMailDTO)))
                .build();
    }

    @Operation(summary = "비밀번호 변경", description = "회원 비밀번호 정보를 변경합니다.")
    @PutMapping("/update-password")
    public ResponseWrapper<Boolean> updatePassword(@Validated(ValidationSequence.class) @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(memberService.updateMemberPassword(updatePasswordDTO)))
                .build();
    }

    @Operation(summary = "회원탈퇴", description = "회원 탈퇴 처리합니다.")
    @DeleteMapping("/{memberNo}")
    public ResponseWrapper<Boolean> withdraw(@PathVariable(value = "memberNo") @Schema(description = "회원번호", example = "1") int memberNo) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(memberService.updateWithdrawMemberInfo(memberNo)))
                .build();
    }

    @Operation(summary = "마이페이지 회원정보 수정", description = "마이페이지 회원 정보를 변경합니다.(닉네임, 생년월일, 성별)")
    @PutMapping("/{memberNo}")
    public ResponseWrapper<MypageDTO> updateMemberInfo(@Positive @PathVariable(value = "memberNo") @Schema(description = "회원번호", example = "1") int memberNo,
                                                       @Valid @RequestBody MypageDTO mypageDTO) {
        return ResponseWrapper.<MypageDTO>builder()
                .data(Collections.singletonList(memberService.updateMemberInfo(memberNo, mypageDTO)))
                .build();
    }
}

