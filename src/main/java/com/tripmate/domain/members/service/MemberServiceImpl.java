package com.tripmate.domain.members.service;

import com.tripmate.common.exception.NoResultException;
import com.tripmate.common.exception.WrongParameterException;
import com.tripmate.domain.ChangePasswordDTO;
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
import org.springframework.util.StringUtils;

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
        MemberDTO signInMemberDTO = memberDAO.selectSignInMemberInfo(signInDTO);

        if (!ObjectUtils.isEmpty(signInMemberDTO) && signInMemberDTO.getSignInRequestCnt() < Const.SIGNIN_LIMIT_CNT) {
            signInDTO = SignInDTO.builder()
                    .memberNo(signInMemberDTO.getMemberNo())
                    .signInRequestCnt(0)
                    .build();
            memberDAO.updateSignInRequestCnt(signInDTO);

            if (ConstCode.MEMBER_STATUS_CODE_ISSUE_TEMPORARY_PASSWORD.equals(signInMemberDTO.getMemberStatusCode())) {
                memberDAO.updateSignInMemberStatus(signInDTO);
            }
        } else {
            MemberDTO checkIdExistDTO = memberDAO.selectSignInRequestCnt(signInDTO);

            if (!ObjectUtils.isEmpty(checkIdExistDTO)) {
                if (checkIdExistDTO.getSignInRequestCnt() >= Const.SIGNIN_LIMIT_CNT) {
                    signInMemberDTO = checkIdExistDTO;
                } else {
                    signInDTO = SignInDTO.builder()
                            .memberNo(checkIdExistDTO.getMemberNo())
                            .signInRequestCnt(checkIdExistDTO.getSignInRequestCnt() + 1)
                            .build();

                    memberDAO.updateSignInRequestCnt(signInDTO);
                }
            }
        }

        if (ObjectUtils.isEmpty(signInMemberDTO)) {
            throw new NoResultException("등록되지 않은 아이디이거나, 아이디 혹은 비밀번호를 잘못 입력했습니다.");
        }

        return signInMemberDTO;
    }

    @Override
    public String findId(MemberDTO memberDTO) {
        String memberId = memberDAO.selectFindId(memberDTO);
        if (!StringUtils.hasText(memberId)) {
            throw new NoResultException("존재하지 않는 회원 정보입니다.");
        }
        return memberId;
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        MemberDTO signInDTO = memberDAO.selectSignInMemberInfo(SignInDTO.builder()
                .memberId(changePasswordDTO.getMemberId())
                .memberPassword(changePasswordDTO.getMemberPassword())
                .build());

        if (ObjectUtils.isEmpty(signInDTO)) {
            throw new NoResultException("현재 비밀번호를 잘못 입력하였습니다.");
        } else {
            memberDAO.updateMemberPassword(changePasswordDTO);
        }

        return true;
    }
}
