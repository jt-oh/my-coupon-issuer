package com.mycouponissuer.coupon_template.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CouponTemplateName {
    private String name;

    public CouponTemplateName(CouponTemplateName name) {
        if (name == null) {
            throw new RuntimeException("CouponTemplateName cannot represent null");
        }

        this.name = name.getName();
    }
}
