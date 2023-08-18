package com.mycouponissuer.coupon_template.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CouponIssueRequest {
    private long couponTemplateId;
    private String ownerId;
}
