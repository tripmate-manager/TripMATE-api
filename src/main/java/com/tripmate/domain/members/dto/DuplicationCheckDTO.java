package com.tripmate.domain.members.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class DuplicationCheckDTO {
    @NonNull
    private String duplicationMemberInfo;
    @NonNull
    private String duplicationCheckType;
}
