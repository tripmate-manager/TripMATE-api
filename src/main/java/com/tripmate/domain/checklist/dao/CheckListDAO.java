package com.tripmate.domain.checklist.dao;

import com.tripmate.domain.checklist.dao.mapper.CheckListDAOMapper;
import com.tripmate.domain.checklist.dto.CheckListDTO;
import com.tripmate.domain.checklist.dto.MyCheckListDTO;
import com.tripmate.domain.checklist.vo.CheckListVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CheckListDAO {
    private final SqlSession sqlSession;

    public int insertCheckList(CheckListDTO checkListDTO) {
        return sqlSession.getMapper(CheckListDAOMapper.class).insertCheckList(checkListDTO);
    }

    public List<CheckListVO> searchTogetherCheckList(String planNo) {
        return sqlSession.getMapper(CheckListDAOMapper.class).searchTogetherCheckList(planNo);
    }

    public List<CheckListVO> searchMyCheckList(MyCheckListDTO myCheckListDTO) {
        return sqlSession.getMapper(CheckListDAOMapper.class).searchMyCheckList(myCheckListDTO);
    }
}
