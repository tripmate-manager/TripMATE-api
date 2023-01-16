package com.tripmate.domain.member.dao.mapper;

import com.tripmate.domain.member.dto.DuplicationCheckDTO;
import com.tripmate.domain.member.dto.MemberDTO;
import com.tripmate.domain.member.dto.MemberMailDTO;

public interface MemberDAOMapper {
    int insertMemberInfo(MemberDTO memberDTO);
    int selectDuplicationCnt(DuplicationCheckDTO duplicationCheckDTO);
    void insertEmailInfo(MemberMailDTO memberMailDTO);
}
