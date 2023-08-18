package com.mycouponissuer.coupon_template.domain;

import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CouponTemplate {
    private CouponTemplateId id;
    private CouponTemplateName name;
    private int quota;

    public CouponTemplate(CouponTemplate couponTemplate) {
        CouponTemplateId couponTemplateId = couponTemplate.getId();
        if (couponTemplateId != null) {
            this.id = new CouponTemplateId(couponTemplateId);
        }

        this.name = couponTemplate.getName();
        this.quota = couponTemplate.getQuota();
    }

    public Coupon issueCoupon(String ownerId) {
        if (! isIssuable()) {
            throw new RuntimeException("CouponTemplate is not issuable");
        }

        return Coupon.builder()
                .couponTemplateId(this.id)
                .ownerId(ownerId)
                .build();
    }

    public int countIssuedCoupons() {
        return 0;
    }

    public boolean isIssuable() {
        return countIssuedCoupons() < this.quota;
    }
}
