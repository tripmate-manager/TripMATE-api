package com.tripmate.domain.common.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
public class CodeDTO {
    @NonNull
    private String commonCode;
    private String commonDetailCode;
    private String commonDetailName;
    private String groupCode1;
    private String groupCode2;
    private String groupCode3;
    private long sortSeq;
    private String useYn;
    private long regNo;
    private Date regDtime;
    private long uptNo;
    private Date uptDtime;
}