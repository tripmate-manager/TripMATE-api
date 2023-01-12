package com.tripmate.domain.member.service;

import com.tripmate.domain.member.dao.MemberDAO;
import com.tripmate.domain.member.dto.MemberDTO;
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
    public void signUp(MemberDTO memberDTO) {
        memberDAO.insertMemberInfo(memberDTO);
    }

    public Integer getMemberIdCount(String memberId) {
        return memberDAO.selectMemberIdCount(memberId);
    }

    @Override
    public Integer getMemberNickNameCount(String memberNickName) {
        return memberDAO.selectMemberNickNameCount(memberNickName);
    }

    @Override
    public Integer getMemberEmailCount(String memberEmail) {
        return memberDAO.selectMemberEmailCount(memberEmail);
    }
}
