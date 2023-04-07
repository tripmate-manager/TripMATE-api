package com.tripmate.domain.dailyplans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteDailyPlanNotificationDTO {
    private String dailyPlanNo;

    private String memberNo;
}
