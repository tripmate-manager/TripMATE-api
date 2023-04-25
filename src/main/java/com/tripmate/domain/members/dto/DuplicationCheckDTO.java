package com.tripmate.domain.members.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class DuplicationCheckDTO {

    private String duplicationMemberInfo;

    private String duplicationCheckType;

}
