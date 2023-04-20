package com.tripmate.domain.plans.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchMemberDTO {
    
    private String searchMemberDiviCode;

    private String searchKeyword;

}
