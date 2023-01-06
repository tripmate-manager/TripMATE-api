package com.tripmate.domain.common.dao;

import com.tripmate.domain.common.vo.CodeVO;
import com.tripmate.domain.common.dao.mapper.CodeDAOMapper;
import com.tripmate.domain.common.dto.CodeDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CodeDAO {
    private final SqlSession sqlSession;

    @Autowired
    public CodeDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<CodeVO> selectCommonDetailCodeList(String commonCode) {
        return sqlSession.getMapper(CodeDAOMapper.class).selectCommonDetailCodeList(commonCode);
    }

    public CodeVO selectCommonDetailCode(CodeDTO codeDTO) {
        return sqlSession.getMapper(CodeDAOMapper.class).selectCommonDetailCode(codeDTO);
    }
}