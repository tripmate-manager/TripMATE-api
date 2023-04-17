package com.tripmate.api.v1.controller;

import com.tripmate.domain.checklist.dto.CheckListDTO;
import com.tripmate.domain.checklist.service.CheckListService;
import com.tripmate.domain.checklist.vo.CheckListVO;
import com.tripmate.domain.common.vo.ResponseWrapper;
import com.tripmate.domain.wishlist.dto.PostDTO;
import com.tripmate.domain.wishlist.vo.PostVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RestController
@Tag(name = "체크리스트 API", description = "CheckList API")
@RequestMapping("v1/checklist")
@Validated
@RequiredArgsConstructor
public class CheckListController {
    private final CheckListService checkListService;

    @Operation(summary = "체크리스트 항목 생성", description = "체크리스트 항목을 생성합니다.")
    @PostMapping("/material")
    public ResponseWrapper<Boolean> createCheckList(@Valid @RequestBody CheckListDTO checkListDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(checkListService.createCheckList(checkListDTO)))
                .build();
    }

    @Operation(summary = "체크리스트 조회", description = "공용 체크리스트(Together) 목록을 조회합니다.")
    @GetMapping("/{planNo}/{checkListTypeCode}")
    public ResponseWrapper<CheckListVO> searchCheckList(@PathVariable(value = "planNo") @RequestParam String planNo) {
        // 20 세팅
        return ResponseWrapper.<CheckListVO>builder()
                .data(checkListService.searchCheckList(planNo))
                .build();
    }
}
