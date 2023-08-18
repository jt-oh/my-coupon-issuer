package com.mycouponissuer.coupon_template.application.dto;

import com.mycouponissuer.coupon_template.domain.CouponTemplate;
import lombok.Getter;

@Getter
public class CouponTemplateDTO {
    private long id;
    private String name;
    private int quota;

    public CouponTemplateDTO(CouponTemplate couponTemplate) {
        this.id = couponTemplate.getId().getId();
        this.name = couponTemplate.getName().getName();
        this.quota = couponTemplate.getQuota();
    }

    public CouponTemplateDTO(CouponTemplateDTO couponTemplateDTO) {
        if (couponTemplateDTO == null) {
            throw new RuntimeException("CouponTemplateDTO cannot represent null");
        }

        this.id = couponTemplateDTO.getId();
        this.name = couponTemplateDTO.getName();
        this.quota = couponTemplateDTO.getQuota();
    }
}
