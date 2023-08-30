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

    public int countIssuedCoupons() {
        return coupons.size();
    }

    public boolean isIssuable() {
        return countIssuedCoupons() < this.quota;
    }

    public boolean addIssuedCoupon(Coupon coupon) throws Exception {
        if (! isIssuable()) {
            throw new Exception("CouponTemplate is not issuable");
        }

        return coupons.add(coupon.getId());
    }
}
