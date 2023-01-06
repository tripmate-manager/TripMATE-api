package com.tripmate.domain.common.dao;

import com.tripmate.domain.common.dao.mapper.CodeDAOMapper;
import com.tripmate.domain.common.dto.CodeDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CodeDAO {
    private final SqlSession sqlSession;

    @Autowired
    public CodeDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<CodeDTO> selectCommonCodeDetailList(String commonCode) {
        return sqlSession.getMapper(CodeDAOMapper.class).selectCommonCodeDetailList(commonCode);
    }

    public CodeDTO selectCommonCodeDetail(String commonCode, String commonDetailCode) {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("commonCode", commonCode);
            put("commonDetailCode", commonDetailCode);
        }};

        return sqlSession.getMapper(CodeDAOMapper.class).selectCommonCodeDetail(map);
    }
}