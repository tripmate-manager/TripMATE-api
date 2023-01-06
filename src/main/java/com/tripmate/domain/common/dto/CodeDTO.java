package com.tripmate.domain.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CodeDTO {
    @NonNull
    private String commonCode;
    private String commonDetailCode;
}