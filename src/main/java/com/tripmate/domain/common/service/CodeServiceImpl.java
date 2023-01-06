package com.tripmate.domain.common.service;

import com.tripmate.domain.common.dao.CodeDAO;
import com.tripmate.domain.common.dto.CodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
    private final CodeDAO codeDAO;

    @Autowired
    public CodeServiceImpl(CodeDAO codeDAO) {
        this.codeDAO = codeDAO;
    }

    @Override
    public List<CodeDTO> getCode(String commonCode) {
        return codeDAO.selectCommonCodeDetailList(commonCode);
    }

    @Override
    public CodeDTO getCode(String commonCode, String commonDetailCode) {
        return codeDAO.selectCommonCodeDetail(commonCode, commonDetailCode);
    }
}