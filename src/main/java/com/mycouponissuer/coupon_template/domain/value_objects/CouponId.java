package com.mycouponissuer.coupon_template.domain.value_objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CouponId {
    private long id;

    public CouponId(CouponId couponId) {
        if (couponId == null) {
            throw new RuntimeException("CouponId cannot represent null");
        }

        this.id = couponId.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (! (obj instanceof CouponId)) {
            return false;
        }

        return this.id == ((CouponId) obj).getId();
    }
}