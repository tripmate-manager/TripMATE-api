package com.tripmate.domain.checklist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "개인 체크리스트 조회 Request DTO")
public class MyCheckListDTO {

    private String planNo;

    private String memberNo;

}
