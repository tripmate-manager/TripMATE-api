package com.tripmate.domain.member.dao.mapper;

import com.tripmate.domain.member.dto.MemberDTO;

public interface MemberDAOMapper {
    void insertMemberInfo(MemberDTO memberDTO);
    Integer selectMemberIdCount(String memberId);
}
