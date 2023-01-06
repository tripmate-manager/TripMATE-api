package com.tripmate.domain.code.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CodeDTO {
    private String commCd;
    private String commDtlCd;
    private String commDtlNm;
    private String grpCd1;
    private String grpCd2;
    private String grpCd3;
    private long sortSeq;
    private String useYn;
    private long regNo;
    private Date regDtime;
    private long uptNo;
    private Date uptDtime;
}