package com.tripmate.domain.member.service;

import com.tripmate.common.exception.WrongParameterException;
import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.member.dao.MemberDAO;
import com.tripmate.domain.member.dto.DuplicationCheckDTO;
import com.tripmate.domain.member.dto.MemberDTO;
import com.tripmate.domain.member.dto.MemberMailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberDAO memberDAO;

    @Autowired
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public int signUp(MemberDTO memberDTO) {
        if (!isDuplicate(DuplicationCheckDTO.builder()
                                            .duplicationMemberInfo(memberDTO.getMemberId())
                                            .duplicationCheckType(ConstCode.DUPLICATION_CHECK_MEMBER_ID)
                                            .build())) {
            throw new WrongParameterException("이미 등록된 ID입니다.");
        }

        if (!isDuplicate(DuplicationCheckDTO.builder()
                                            .duplicationMemberInfo(memberDTO.getEmail())
                                            .duplicationCheckType(ConstCode.DUPLICATION_CHECK_EMAIL)
                                            .build())) {
            throw new WrongParameterException("이미 등록된 메일주소 입니다.");
        }

        if (!isDuplicate(DuplicationCheckDTO.builder()
                                            .duplicationMemberInfo(memberDTO.getNickName())
                                            .duplicationCheckType(ConstCode.DUPLICATION_CHECK_NICK_NAME)
                                            .build())) {
            throw new WrongParameterException("이미 등록된 닉네임 입니다.");
        }

        return memberDAO.insertMemberInfo(memberDTO);
    }

    @Override
    public boolean isDuplicate(DuplicationCheckDTO duplicationCheckDTO) {
        return memberDAO.selectDuplicationCnt(duplicationCheckDTO) < 1;
    }

    @Override
    public void signUpMailConfirm(MemberMailDTO memberMailDTO) {
        memberDAO.updateSignUpEmailConfirm(memberMailDTO);
    }
}
