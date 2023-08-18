package com.mycouponissuer.coupon_template.application.outbound_ports;

import com.mycouponissuer.coupon_template.domain.Coupon;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponId;

import java.util.ArrayList;

public interface CouponRepository {
    public Coupon save(Coupon coupon);
    public ArrayList<Coupon> all();
    public Coupon find(CouponId id);
}
