package com.tripmate.domain.members.service;

import com.tripmate.common.exception.WrongParameterException;
import com.tripmate.domain.common.Const;
import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.members.dao.MemberDAO;
import com.tripmate.domain.members.dto.DuplicationCheckDTO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import com.tripmate.domain.members.dto.SignInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public int signUp(MemberDTO memberDTO) {
        if (isDuplicate(DuplicationCheckDTO.builder()
                .duplicationMemberInfo(memberDTO.getMemberId())
                .duplicationCheckType(ConstCode.DUPLICATION_CHECK_MEMBER_ID)
                .build())) {
            throw new WrongParameterException("이미 등록된 ID입니다.");
        }

        if (isDuplicate(DuplicationCheckDTO.builder()
                .duplicationMemberInfo(memberDTO.getEmail())
                .duplicationCheckType(ConstCode.DUPLICATION_CHECK_EMAIL)
                .build())) {
            throw new WrongParameterException("이미 등록된 메일주소 입니다.");
        }

        if (isDuplicate(DuplicationCheckDTO.builder()
                .duplicationMemberInfo(memberDTO.getNickName())
                .duplicationCheckType(ConstCode.DUPLICATION_CHECK_NICK_NAME)
                .build())) {
            throw new WrongParameterException("이미 등록된 닉네임 입니다.");
        }

        return memberDAO.insertMemberInfo(memberDTO);
    }

    @Override
    public boolean isDuplicate(DuplicationCheckDTO duplicationCheckDTO) {
        return memberDAO.selectDuplicationCnt(duplicationCheckDTO) > 0;
    }

    @Override
    public void signUpMailConfirm(MemberMailDTO memberMailDTO) {
        memberDAO.updateSignUpEmailConfirm(memberMailDTO);
    }

    @Override
    public MemberDTO signIn(SignInDTO signInDTO) {
        MemberDTO memberDTO = memberDAO.selectSignInMemberInfo(signInDTO);

        if (!ObjectUtils.isEmpty(memberDTO) && memberDTO.getSignInRequestCnt() < Const.SIGNIN_LIMIT_CNT) {
            signInDTO = SignInDTO.builder()
                    .memberNo(memberDTO.getMemberNo())
                    .memberId(memberDTO.getMemberId())
                    .memberPassword(memberDTO.getMemberPassword())
                    .signInRequestCnt(0)
                    .build();
            memberDAO.updateSignInRequestCnt(signInDTO);
        } else {
            MemberDTO memberDTO1 = memberDAO.selectSignInRequestCnt(signInDTO);

            if (!ObjectUtils.isEmpty(memberDTO1)) {
                if (memberDTO1.getSignInRequestCnt() >= Const.SIGNIN_LIMIT_CNT) {
                    memberDTO = memberDTO1;
                } else {
                    signInDTO = SignInDTO.builder()
                            .memberNo(memberDTO1.getMemberNo())
                            .memberId(memberDTO1.getMemberId())
                            .memberPassword(memberDTO1.getMemberPassword())
                            .signInRequestCnt(memberDTO1.getSignInRequestCnt() + 1)
                            .build();

                    memberDAO.updateSignInRequestCnt(signInDTO);
                }
            }
        }

        return memberDTO;
    }
}
