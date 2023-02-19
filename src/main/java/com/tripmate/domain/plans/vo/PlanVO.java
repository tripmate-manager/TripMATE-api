package com.tripmate.domain.plans.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "플랜 조회 Response VO")
public class PlanVO {
    private int planNo;

    private String planTitle;

    private String planDescription;

    private String publicYn;

    private String tripStartDate;

    private String tripEndDate;

    private int tripTerm;

    private int likeRegistrationCnt;

    private int achieveRate;

    private int views;

    private double reviewAverageScore;

    private String leadYn;

    private String registrationDateTime;

    private List<PlanAddressVO> planAddressList;

    private List<PlanAttributeVO> planAttributeList;
}