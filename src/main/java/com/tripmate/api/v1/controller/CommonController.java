package com.tripmate.api.v1.controller;

import com.tripmate.domain.common.dto.CodeDTO;
import com.tripmate.domain.common.service.CodeService;
import com.tripmate.domain.common.vo.CodeVO;
import com.tripmate.domain.common.vo.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "공통 API", description = "Common API")
@RequestMapping("/v1/common")
public class CommonController {
    private final CodeService codeService;

    @Autowired
    public CommonController(CodeService codeService) {
        this.codeService = codeService;
    }

    @Operation(summary = "공통 상세 코드 목록 조회", description = "commonCode 기준 ST_COMM_CD_DTL 테이블에서 데이터 조회")
    @GetMapping("codes/{commonCode}")
    public ResponseWrapper<CodeVO> getCode(@PathVariable(value = "commonCode") @Schema(example = "MB001") String commonCode) {
        return ResponseWrapper.<CodeVO>builder()
                              .data(codeService.getCode(commonCode))
                              .build();
    }

    @Operation(summary = "공통 상세 코드 조회", description = "commonCode, commonDetailCode 기준 ST_COMM_CD_DTL 테이블에서 데이터 조회")
    @GetMapping("codes/{commonCode}/{commonDetailCode}")
    public ResponseWrapper<CodeVO> getCode(@PathVariable(value = "commonCode") @Schema(example = "MB001") String commonCode,
                                           @PathVariable(value = "commonDetailCode") @Schema(example = "10") String commonDetailCode) {
        return ResponseWrapper.<CodeVO>builder()
                              .data(Collections.singletonList(codeService.getCode(CodeDTO.builder()
                                                                                         .commonCode(commonCode)
                                                                                         .commonDetailCode(commonDetailCode)
                                                                                         .build())))
                              .build();
    }
}
