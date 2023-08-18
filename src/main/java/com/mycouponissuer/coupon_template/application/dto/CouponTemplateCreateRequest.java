package com.mycouponissuer.coupon_template.application.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
public class CouponTemplateCreateRequest {
    private String name;
    private int quota;

    public CouponTemplateCreateRequest(CouponTemplateCreateRequest couponTemplateCreateRequest) {
        if (couponTemplateCreateRequest == null) {
            throw new RuntimeException("CouponTemplateCreateRequest cannot represent null");
        }

        this.name = couponTemplateCreateRequest.getName();
        this.quota = couponTemplateCreateRequest.getQuota();
    }
}
