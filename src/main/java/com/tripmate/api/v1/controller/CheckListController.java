package com.tripmate.api.v1.controller;

import com.tripmate.domain.checklist.dto.CheckListDTO;
import com.tripmate.domain.checklist.dto.DeleteCheckListDTO;
import com.tripmate.domain.checklist.dto.MyCheckListDTO;
import com.tripmate.domain.checklist.service.CheckListService;
import com.tripmate.domain.checklist.vo.CheckListVO;
import com.tripmate.domain.common.vo.ResponseWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @PostMapping("/{planNo}")
    public ResponseWrapper<Boolean> insertCheckList(@PathVariable(value = "planNo") String planNo,
                                                    @Valid @RequestBody CheckListDTO checkListDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(checkListService.createCheckList(planNo, checkListDTO)))
                .build();
    }

    @Operation(summary = "공용 체크리스트 조회", description = "공용 체크리스트(Together) 목록을 조회합니다.")
    @GetMapping("/{planNo}")
    public ResponseWrapper<CheckListVO> searchTogetherCheckList(@PathVariable(value = "planNo") String planNo) {
        return ResponseWrapper.<CheckListVO>builder()
                .data(checkListService.searchTogetherCheckList(planNo))
                .build();
    }

    @Operation(summary = "개인 체크리스트 조회", description = "개인 체크리스트(My) 목록을 조회합니다.")
    @GetMapping("/{planNo}/{memberNo}")
    public ResponseWrapper<CheckListVO> searchMyCheckList(@PathVariable(value = "planNo") String planNo,
                                                          @PathVariable(value = "memberNo") String memberNo) {
        return ResponseWrapper.<CheckListVO>builder()
                .data(checkListService.searchMyCheckList(MyCheckListDTO.builder()
                        .planNo(planNo)
                        .memberNo(memberNo)
                        .build()))
                .build();
    }

    @Operation(summary = "체크리스트 항목 삭제", description = "체크리스트 항목을 삭제합니다.")
    @PostMapping("/delete-checklist")
    public ResponseWrapper<Boolean> deleteCheckList(@Valid @RequestBody DeleteCheckListDTO deleteCheckListDTO) {
        return ResponseWrapper.<Boolean>builder()
                .data(Collections.singletonList(checkListService.deleteCheckList(deleteCheckListDTO)))
                .build();
    }

}
