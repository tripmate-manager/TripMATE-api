package com.tripmate.domain.dailyplans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DailyPlanByDayDTO {
    private String planNo;

    private String memberNo;

    private String dayGroup;
}
