package com.tripmate.domain.members.dao.mapper;

import com.tripmate.domain.members.dto.ChangePasswordDTO;
import com.tripmate.domain.members.dto.DuplicationCheckDTO;
import com.tripmate.domain.members.dto.MemberDTO;
import com.tripmate.domain.members.dto.MemberMailDTO;
import com.tripmate.domain.members.dto.MypageDTO;
import com.tripmate.domain.members.dto.SignInDTO;

public interface MemberDAOMapper {
    int insertMemberInfo(MemberDTO memberDTO);
    int selectDuplicationCnt(DuplicationCheckDTO duplicationCheckDTO);
    void insertEmailInfo(MemberMailDTO memberMailDTO);
    int updateSignUpEmailConfirm(MemberMailDTO memberMailDTO);
    MemberDTO selectSignInMemberInfo(SignInDTO signInDTO);
    void updateSignInReqCntAndMbrStatusCd(SignInDTO signInDTO);
    MemberDTO selectSignInRequestCnt(SignInDTO signInDTO);
    String selectMbrIdWithMbrNmAndEmail(MemberDTO memberDTO);
    MemberDTO selectMbrNoAndStatusCd(MemberMailDTO memberMailDTO);
    void updateMbrPwdAndStatusCd(MemberDTO memberDTO);
    int selectAuthEmailCnt(MemberMailDTO memberMailDTO);
    void updateEmailInfo(MemberMailDTO memberMailDTO);
    int updateMbrPwd(ChangePasswordDTO changePasswordDTO);
    int updateWithdrawMemberInfo(int memberNo);
    MemberDTO selectMemberInfoWithMbrNo(int memberNo);
    int updateMemberInfo(MypageDTO mypageDTO);
}
