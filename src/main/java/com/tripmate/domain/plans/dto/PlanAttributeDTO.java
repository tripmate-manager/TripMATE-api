package com.tripmate.domain.plans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PlanAttributeDTO {

    private int planNo;

    private String attributeTypeCode;

}