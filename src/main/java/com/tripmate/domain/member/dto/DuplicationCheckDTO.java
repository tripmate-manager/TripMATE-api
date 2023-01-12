package com.tripmate.domain.member.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class DuplicationCheckDTO {
    @NonNull
    private String duplicationMemberInfo;
    @NonNull
    private String duplicationCheckType;
}
