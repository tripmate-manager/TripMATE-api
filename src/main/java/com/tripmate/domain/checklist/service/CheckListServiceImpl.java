package com.tripmate.domain.checklist.service;

import com.tripmate.domain.checklist.dao.CheckListDAO;
import com.tripmate.domain.checklist.dto.CheckListDTO;
import com.tripmate.domain.checklist.vo.CheckListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckListServiceImpl implements CheckListService{
    private final CheckListDAO checkListDAO;

    @Override
    public boolean createCheckList(CheckListDTO checkListDTO) {
        return checkListDAO.insertCheckList(checkListDTO) == 1;
    }

    @Override
    public List<CheckListVO> searchCheckList(String planNo) {
        return checkListDAO.searchTogetherCheckList(planNo);
    }
}
