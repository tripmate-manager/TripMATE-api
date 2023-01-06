package com.tripmate.api.v1.controller;

import com.tripmate.domain.code.dto.CodeDTO;
import com.tripmate.domain.code.service.CodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "공통 API", description = "Common API")
@RequestMapping("/v1/common")
public class CommonController {
    private final Logger logger = LogManager.getLogger(CommonController.class);

    @Autowired
    private CodeService codeService;

    @Operation(summary = "공통 상세 코드 목록 조회", description = "commCd 기준 ST_COMM_CD_DTL 테이블에서 데이터 조회")
    @GetMapping("codeList")
    public List<CodeDTO> searchCodeListWithCommCd(@RequestParam(value = "commCd") String commCd) {
        return codeService.searchCodeListWithCommCd(commCd);
    }

    @Operation(summary = "공통 상세 코드 조회", description = "commCd, commDtlCd 기준 ST_COMM_CD_DTL 테이블에서 데이터 조회")
    @GetMapping("code")
    public CodeDTO getCodeWithCommCdAndCommDtlCd(@RequestParam(value = "commCd") String commCd,
                                                 @RequestParam(value = "commDtlCd") String commDtlCd) {
        return codeService.getCodeWithCommCdAndCommDtlCd(commCd, commDtlCd);
    }
}
