package com.tripmate.domain.accountbook.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "여행가계부 항목 삭제 Request DTO")
public class DeleteAccountBookDTO {

    @NotBlank(message = "회원 번호를 입력해주세요.")
    @Schema(description = "회원 번호", example = "1")
    private String memberNo;

    @NotBlank(message = "플랜 일자를 입력해주세요.")
    @Schema(description = "플랜 일자", example = "1")
    private String dayGroup;

    @Schema(description = "여행가계부 항목 번호 리스트")
    private List<String> accountNoList;
    
}
