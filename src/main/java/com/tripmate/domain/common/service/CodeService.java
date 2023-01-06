package com.tripmate.domain.common.service;

import com.tripmate.domain.common.vo.CodeVO;
import com.tripmate.domain.common.dto.CodeDTO;

import java.util.List;

public interface CodeService {
    List<CodeVO> getCode(String commCd);
    CodeVO getCode(CodeDTO codeDTO);
}