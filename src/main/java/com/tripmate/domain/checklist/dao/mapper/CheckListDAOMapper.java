package com.tripmate.domain.checklist.dao.mapper;

import com.tripmate.domain.checklist.dto.CheckListDTO;
import com.tripmate.domain.checklist.dto.MyCheckListDTO;
import com.tripmate.domain.checklist.dto.UpdateCheckYnDTO;
import com.tripmate.domain.checklist.vo.CheckListVO;

import java.util.List;

public interface CheckListDAOMapper {
    int insertCheckList(CheckListDTO checkListDTO);
    List<CheckListVO> searchTogetherCheckList(String planNo);
    List<CheckListVO> searchMyCheckList(MyCheckListDTO myCheckListDTO);
    int deleteCheckList(List<String> materialNoList);
    int updateCheckYn(UpdateCheckYnDTO updateCheckYnDTO);
    String getCheckMemberNo(UpdateCheckYnDTO updateCheckYnDTO);
}
