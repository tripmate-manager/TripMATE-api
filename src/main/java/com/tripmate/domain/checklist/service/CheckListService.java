package com.tripmate.domain.checklist.service;

import com.tripmate.domain.checklist.dto.CheckListDTO;
import com.tripmate.domain.checklist.dto.DeleteCheckListDTO;
import com.tripmate.domain.checklist.dto.MyCheckListDTO;
import com.tripmate.domain.checklist.vo.CheckListVO;

import java.util.List;

public interface CheckListService {
    boolean createCheckList(String planNo, CheckListDTO checkListDTO);
    List<CheckListVO> searchTogetherCheckList(String planNo);
    List<CheckListVO> searchMyCheckList(MyCheckListDTO myCheckListDTO);
    boolean deleteCheckList(DeleteCheckListDTO deleteCheckListDTO);
}
