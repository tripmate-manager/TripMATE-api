package com.tripmate.domain.code.dao;

import com.tripmate.domain.code.dao.mapper.CodeDAOMapper;
import com.tripmate.domain.code.dto.CodeDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CodeDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<CodeDTO> searchCodeListWithCommCd(CodeDTO param) {
        CodeDAOMapper mapper = sqlSession.getMapper(CodeDAOMapper.class);
        return mapper.searchCodeListWithCommCd(param);
    }

    public CodeDTO getCodeWithCommCdAndCommDtlCd(CodeDTO param) {
        CodeDAOMapper mapper = sqlSession.getMapper(CodeDAOMapper.class);
        return mapper.getCodeWithCommCdAndCommDtlCd(param);
    }
}