package com.tripmate.domain.plans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "알림 확인 일시 수정 Request DTO")
public class UpdateNotificationReadDateTimeDTO {
    @Min(value = 1, message = "회원 번호는 필수이며, 양수만 입력 가능합니다.")
    private String memberNo;

    @Min(value = 1, message = "알림 번호는 필수이며, 양수만 입력 가능합니다.")
    private String notificationNo;
}
