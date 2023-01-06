package com.tripmate.domain.code.service;

import com.tripmate.domain.code.dao.CodeDAO;
import com.tripmate.domain.code.dto.CodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeDAO codeDAO;

    @Override
    public List<CodeDTO> searchCodeListWithCommCd(String commCd) {
        CodeDTO param = new CodeDTO();
        param.setCommCd(commCd);
        return codeDAO.searchCodeListWithCommCd(param);
    }

    @Override
    public CodeDTO getCodeWithCommCdAndCommDtlCd(String commCd, String commDtlCd) {
        CodeDTO param = new CodeDTO();
        param.setCommCd(commCd);
        param.setCommDtlCd(commDtlCd);
        return codeDAO.getCodeWithCommCdAndCommDtlCd(param);
    }
}