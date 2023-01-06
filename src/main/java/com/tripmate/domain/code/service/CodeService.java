package com.tripmate.domain.code.service;

import com.tripmate.domain.code.dto.CodeDTO;

import java.util.List;

public interface CodeService {
    List<CodeDTO> searchCodeListWithCommCd(String commCd);
    CodeDTO getCodeWithCommCdAndCommDtlCd(String commCd, String commDtlCd);
}