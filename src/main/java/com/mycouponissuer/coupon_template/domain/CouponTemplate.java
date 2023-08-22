package com.mycouponissuer.coupon_template.domain;

import com.mycouponissuer.coupon_template.domain.value_objects.CouponId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CouponTemplate {
    private CouponTemplateId id;
    private CouponTemplateName name;
    private int quota;
    private ArrayList<CouponId> coupons;

    public CouponTemplate(CouponTemplate couponTemplate) {
        CouponTemplateId couponTemplateId = couponTemplate.getId();
        if (couponTemplateId != null) {
            this.id = new CouponTemplateId(couponTemplateId);
        }

        this.name = couponTemplate.getName();
        this.quota = couponTemplate.getQuota();
        this.coupons = couponTemplate.getCoupons();
    }

    public Coupon issueCoupon(String ownerId) {
        if (! isIssuable()) {
            throw new RuntimeException("CouponTemplate is not issuable");
        }

        // Todo:
        //  1. assign id when creating Coupon
        //  2. separate Coupon Creation to CouponFactory
        Coupon coupon = Coupon.builder()
                .couponTemplateId(this.id)
                .ownerId(ownerId)
                .build();

//        coupons.add(coupon.getId());

        return coupon;
    }

    public int countIssuedCoupons() {
        return coupons.size();
    }

    public boolean isIssuable() {
        return countIssuedCoupons() < this.quota;
    }
}
