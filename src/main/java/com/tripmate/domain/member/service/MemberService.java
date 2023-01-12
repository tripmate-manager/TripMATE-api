package com.tripmate.domain.member.service;

import com.tripmate.domain.member.dto.MemberDTO;

public interface MemberService {
    void signUp(MemberDTO memberDTO);
    Integer getMemberIdCount(String memberId);
    Integer getMemberNickNameCount(String memberNickName);
    Integer getMemberEmailCount(String memberEmail);
}
