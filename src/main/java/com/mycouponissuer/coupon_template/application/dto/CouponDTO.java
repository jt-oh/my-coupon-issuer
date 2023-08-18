package com.mycouponissuer.coupon_template.application.dto;

import com.mycouponissuer.coupon_template.domain.Coupon;
import lombok.Getter;

@Getter

public class CouponDTO {
    private long id;
    private long couponTemplateId;
    public CouponDTO(Coupon coupon) {
        this.id = coupon.getId().getId();
        this.couponTemplateId = coupon.getCouponTemplateId().getId();
    }

    public CouponDTO(CouponDTO couponDTO) {
        if (couponDTO == null) {
            throw new RuntimeException("CouponDTO cannot represent null");
        }

        this.id = couponDTO.getId();
        this.couponTemplateId = couponDTO.getCouponTemplateId();
    }
}
