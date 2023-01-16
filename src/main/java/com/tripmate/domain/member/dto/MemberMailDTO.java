package com.tripmate.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberMailDTO {
    @NonNull
    private String email;

    @NonNull
    private String key;

    @NonNull
    private String mailTypeCode;
}
