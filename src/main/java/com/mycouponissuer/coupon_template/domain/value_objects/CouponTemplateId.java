package com.mycouponissuer.coupon_template.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class CouponTemplateId {
    private long id;

    public CouponTemplateId(CouponTemplateId couponTemplateId) {
        if (couponTemplateId == null) {
            throw new RuntimeException("CouponTemplateId cannot represent null");
        }

        this.id = couponTemplateId.getId();
    }
}
