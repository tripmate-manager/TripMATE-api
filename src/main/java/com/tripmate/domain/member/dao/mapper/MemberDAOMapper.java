package com.tripmate.domain.member.dao.mapper;

import com.tripmate.domain.member.dto.DuplicationCheckDTO;
import com.tripmate.domain.member.dto.MemberDTO;

public interface MemberDAOMapper {
    void insertMemberInfo(MemberDTO memberDTO);
    boolean selectDuplicationCnt(DuplicationCheckDTO duplicationCheckDTO);
}
