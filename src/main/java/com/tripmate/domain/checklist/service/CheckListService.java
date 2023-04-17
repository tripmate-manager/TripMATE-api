package com.tripmate.domain.checklist.service;

import com.tripmate.domain.checklist.dto.CheckListDTO;
import com.tripmate.domain.checklist.vo.CheckListVO;

import java.util.List;

public interface CheckListService {
    boolean createCheckList(CheckListDTO checkListDTO);
    List<CheckListVO> searchCheckList(String planNo);
}
