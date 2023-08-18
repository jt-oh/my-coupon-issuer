package com.mycouponissuer.coupon_template.domain;

import com.mycouponissuer.coupon_template.domain.value_objects.CouponId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Coupon {
    private CouponId id;
    private CouponTemplateId couponTemplateId;
    private String ownerId;

    public Coupon(Coupon coupon) {
        CouponId id = coupon.getId();
        if (id != null) {
            this.id = new CouponId(id);
        }

        this.ownerId = coupon.getOwnerId();
        this.couponTemplateId = coupon.getCouponTemplateId();
    }
}