package com.tripmate.domain.searchplan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchAttributeDTO {

    @Pattern(regexp = "^[123456]0$", message = "연령대는 10, 20, 30, 40, 50, 60만 입력 가능합니다.")
    @Schema(description = "연령대")
    private String age;

    @Pattern(regexp = "^[12]0$", message = "성별코드는 10, 20만 입력 가능합니다.")
    @Schema(description = "성별")
    private String genderCode;

    @Min(value = 0, message = "인원은 양수만 입력 가능합니다.")
    @Max(value = 40, message = "인원은 최대 40까지 입력 가능합니다.")
    @Schema(description = "인원")
    private int personnel;

    @Schema(description = "여행지 리스트")
    private List<String> tripAddressList;

    @Schema(description = "장소 리스트")
    private List<String> tripSpotList;

    @Pattern(regexp = "^([369]0|[0-7]|91|14|21)$", message = "여행 기간은 0~6(일), 7(1주), 14(2주), 21(3주), 30(1개월), 60(2개월), 90(3개월), 91(3개월 이상)만 입력 가능합니다.")
    @Schema(description = "여행 기간 [0~6(일), 7(1주), 14(2주), 21(3주), 30(1개월), 60(2개월), 90(3개월), 91(3개월 이상)]")
    private String tripTerm;

    @Schema(description = "플랜 테마 리스트")
    private List<String> planThemeList;

}
