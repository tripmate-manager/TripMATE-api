package com.tripmate.domain.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CodeDTO {
    @NonNull
    private String commonCode;
    @NonNull
    private String commonDetailCode;
}