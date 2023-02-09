package com.tripmate.domain.members.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.common.exception.NoResultException;
import com.tripmate.common.exception.WrongParameterException;
import com.tripmate.domain.common.Const;
import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.members.dao.MemberDAO;
import com.tripmate.domain.members.dto.ChangePasswordDTO;
import com.tripmate.domain.members.dto.DuplicationCheckDTO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import com.tripmate.domain.members.dto.MypageDTO;
import com.tripmate.domain.members.dto.SignInDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String signUpMailConfirm(MemberMailDTO memberMailDTO) {
        if (memberDAO.updateSignUpEmailConfirm(memberMailDTO) != 1) {
            throw new GuideMessageException("비밀번호 변경 처리 중 오류가 발생하였습니다.");
        }
        return memberMailDTO.getTo();
    }

    @Override
    public MemberDTO signIn(SignInDTO signInDTO) {
        MemberDTO signInMemberDTO = memberDAO.selectSignInMemberInfo(signInDTO);

        if (signInMemberDTO != null && signInMemberDTO.getSignInRequestCnt() < Const.SIGNIN_LIMIT_CNT) {
            signInDTO = SignInDTO.builder()
                    .memberNo(signInMemberDTO.getMemberNo())
                    .signInRequestCnt(0)
                    .memberStatusCode(signInMemberDTO.getMemberStatusCode())
                    .build();

            memberDAO.updateSignInRequestCntAndStatusCode(signInDTO);
        } else {
            MemberDTO checkIdExistDTO = memberDAO.selectSignInRequestCnt(signInDTO);

            if (checkIdExistDTO != null) {
                if (checkIdExistDTO.getSignInRequestCnt() >= Const.SIGNIN_LIMIT_CNT) {
                    signInMemberDTO = checkIdExistDTO;
                } else {
                    signInDTO = SignInDTO.builder()
                            .memberNo(checkIdExistDTO.getMemberNo())
                            .signInRequestCnt(checkIdExistDTO.getSignInRequestCnt() + 1)
                            .build();

                    memberDAO.updateSignInRequestCntAndStatusCode(signInDTO);
                }
            }
        }

        if (signInMemberDTO == null) {
            throw new NoResultException("등록되지 않은 아이디이거나, 아이디 혹은 비밀번호를 잘못 입력했습니다.");
        }

        return signInMemberDTO;
    }

    @Override
    public String findId(MemberDTO memberDTO) {
        String memberId = memberDAO.selectMemberIdWithNameAndEmail(memberDTO);
        if (StringUtils.isEmpty(memberId)) {
            throw new NoResultException("존재하지 않는 회원 정보입니다.");
        }
        return memberId;
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        MemberDTO memberIdExistCheckDTO = memberDAO.selectSignInRequestCnt(SignInDTO.builder()
                .memberId(changePasswordDTO.getMemberId())
                .build());

        if (memberIdExistCheckDTO == null) {
            throw new NoResultException("회원 ID에 해당하는 회원 정보가 존재하지 않습니다.");
        }

        MemberDTO memberDTO = memberDAO.selectMemberInfoWithMemberNo(changePasswordDTO.getMemberNo());

        if (memberDTO == null) {
            throw new NoResultException("현재 비밀번호를 잘못 입력하였습니다.");
        }

        return memberDAO.updateMemberPassword(changePasswordDTO) == 1;
    }

    @Override
    public boolean updateWithdrawMemberInfo(int memberNo) {
        MemberDTO memberDTO = memberDAO.selectMemberInfoWithMemberNo(memberNo);

        if (memberDTO == null) {
            throw new NoResultException("잘못된 비밀번호입니다.");
        }

        return memberDAO.updateWithdrawMemberInfo(memberNo) == 1;
    }

    @Override
    public MypageDTO updateMemberInfo(int memberNo, MypageDTO mypageDTO) {
        if (memberNo != mypageDTO.getMemberNo()) {
            throw new WrongParameterException("회원정보 변경 처리 중 오류가 발생하였습니다.");
        }

        if (!mypageDTO.getNickName().equals(memberDAO.selectMemberInfoWithMemberNo(memberNo).getNickName())) {
            if (isDuplicate(DuplicationCheckDTO.builder()
                    .duplicationMemberInfo(mypageDTO.getNickName())
                    .duplicationCheckType(ConstCode.DUPLICATION_CHECK_NICK_NAME)
                    .build())) {
                throw new WrongParameterException("이미 등록된 닉네임 입니다.");
            }
        }

        if (memberDAO.updateMemberInfo(mypageDTO) != 1) {
            throw new GuideMessageException("회원정보 변경 처리 중 오류가 발생하였습니다.");
        }

        MemberDTO updateMemberInfoResult = memberDAO.selectMemberInfoWithMemberNo(mypageDTO.getMemberNo());

        return MypageDTO.builder()
                .memberNo(updateMemberInfoResult.getMemberNo())
                .nickName(updateMemberInfoResult.getNickName())
                .birthDay(updateMemberInfoResult.getBirthDay())
                .genderCode(updateMemberInfoResult.getGenderCode()).build();
    }
}
