package com.tripmate.domain.searchplan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchUserRecommendationDTO {

    private String memberNo;

    private String age;

    private String genderCode;

}
