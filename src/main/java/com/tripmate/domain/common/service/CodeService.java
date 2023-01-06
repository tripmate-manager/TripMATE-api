package com.tripmate.domain.common.service;

import com.tripmate.domain.common.dto.CodeDTO;

import java.util.List;

public interface CodeService {
    List<CodeDTO> getCode(String commCd);
    CodeDTO getCode(String commCd, String commDtlCd);
}