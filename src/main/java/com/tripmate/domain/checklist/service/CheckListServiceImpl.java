package com.tripmate.domain.checklist.service;

import com.tripmate.common.exception.GuideMessageException;
import com.tripmate.common.exception.WrongParameterException;
import com.tripmate.domain.checklist.dao.CheckListDAO;
import com.tripmate.domain.checklist.dto.CheckListDTO;
import com.tripmate.domain.checklist.dto.DeleteCheckListDTO;
import com.tripmate.domain.checklist.dto.MyCheckListDTO;
import com.tripmate.domain.checklist.dto.UpdateCheckYnDTO;
import com.tripmate.domain.checklist.vo.CheckListVO;
import com.tripmate.domain.common.ConstCode;
import com.tripmate.domain.plans.dao.PlanDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckListServiceImpl implements CheckListService{
    private final CheckListDAO checkListDAO;
    private final PlanDAO planDAO;

    @Override
    public boolean createCheckList(String planNo, CheckListDTO checkListDTO) {
        if (!planNo.equals(checkListDTO.getPlanNo())) {
            throw new WrongParameterException("체크리스트 생성 처리 중 오류가 발생하였습니다.");
        }

        return checkListDAO.insertCheckList(checkListDTO) == 1;
    }

    @Override
    public List<CheckListVO> searchTogetherCheckList(String planNo) {
        return checkListDAO.searchTogetherCheckList(planNo);
    }

    @Override
    public List<CheckListVO> searchMyCheckList(MyCheckListDTO myCheckListDTO) {
        return checkListDAO.searchMyCheckList(myCheckListDTO);
    }

    @Override
    public boolean deleteCheckList(DeleteCheckListDTO deleteCheckListDTO) {
        if (deleteCheckListDTO.getCheckListTypeCode().equals(ConstCode.CHECKLIST_TYPE_CODE_TOGETHER)) {
            if (!deleteCheckListDTO.getMemberNo().equals(planDAO.getPlanLeaderMemberNo(deleteCheckListDTO.getPlanNo()))) {
                throw new GuideMessageException("공용 체크리스트 항목은 플랜 리더만 변경 가능합니다.");
            }
        }

        return checkListDAO.deleteCheckList(deleteCheckListDTO.getMaterialNoList()) == deleteCheckListDTO.getMaterialNoList().size();
    }

    @Override
    public boolean updateCheckYn(String materialNo, UpdateCheckYnDTO updateCheckYnDTO) {

        if (ConstCode.CHECKLIST_TYPE_CODE_TOGETHER.equals(updateCheckYnDTO.getCheckListTypeCode())) {
            if (updateCheckYnDTO.getMaterialNo().equals(checkListDAO.getCheckMemberNo(updateCheckYnDTO))) {
                throw new GuideMessageException("이미 체크된 항목입니다.");
            }
        }

        return checkListDAO.updateCheckYn(updateCheckYnDTO) == 1;
    }

}
