package com.tripmate.domain.code.dao.mapper;

import com.tripmate.domain.code.dto.CodeDTO;

import java.util.List;

public interface CodeDAOMapper {
    List<CodeDTO> searchCodeListWithCommCd(CodeDTO param);
    CodeDTO getCodeWithCommCdAndCommDtlCd(CodeDTO param);
}