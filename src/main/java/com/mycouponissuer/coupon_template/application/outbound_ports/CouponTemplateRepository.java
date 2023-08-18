package com.mycouponissuer.coupon_template.application.outbound_ports;

import com.mycouponissuer.coupon_template.domain.CouponTemplate;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;

import java.util.ArrayList;

public interface CouponTemplateRepository {
    public CouponTemplate save(CouponTemplate couponTemplate);
    public ArrayList<CouponTemplate> all();
    public CouponTemplate find(CouponTemplateId id);
}
