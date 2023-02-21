package com.tripmate.domain.members.dao.mapper;

import com.tripmate.domain.members.dto.UpdatePasswordDTO;
import com.tripmate.domain.members.dto.DuplicationCheckDTO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import com.tripmate.domain.members.dto.MypageDTO;
import com.tripmate.domain.members.dto.SignInDTO;

public interface MemberDAOMapper {
    int insertMemberInfo(MemberDTO memberDTO);
    int getDuplicationCnt(DuplicationCheckDTO duplicationCheckDTO);
    void insertEmailInfo(MemberMailDTO memberMailDTO);
    int updateSignUpEmailConfirm(MemberMailDTO memberMailDTO);
    MemberDTO getSignInMemberInfo(SignInDTO signInDTO);
    void updateSignInReqestCntAndMemberStatusCd(SignInDTO signInDTO);
    MemberDTO getSignInRequestCnt(SignInDTO signInDTO);
    String getMemberIdWithMemberNameAndEmail(MemberDTO memberDTO);
    MemberDTO getMemberNoAndStatusCd(MemberMailDTO memberMailDTO);
    void updateMemberPasswordAndStatusCd(MemberDTO memberDTO);
    int getAuthEmailCnt(MemberMailDTO memberMailDTO);
    void updateEmailInfo(MemberMailDTO memberMailDTO);
    int updateMemberPassword(UpdatePasswordDTO updatePasswordDTO);
    int updateWithdrawMemberInfo(int memberNo);
    MemberDTO getMemberInfoWithMemberNo(int memberNo);
    int updateMemberInfo(MypageDTO mypageDTO);
}
