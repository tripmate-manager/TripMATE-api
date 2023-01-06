package com.tripmate.domain.common.dao.mapper;

import com.tripmate.domain.common.vo.CodeVO;
import com.tripmate.domain.common.dto.CodeDTO;

import java.util.List;

public interface CodeDAOMapper {
    List<CodeVO> selectCommonDetailCodeList(String commonCode);
    CodeVO selectCommonDetailCode(CodeDTO codeDTO);
}