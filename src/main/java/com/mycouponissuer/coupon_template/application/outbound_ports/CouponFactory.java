package com.mycouponissuer.coupon_template.application.outbound_ports;

import com.mycouponissuer.coupon_template.application.outbound_ports.dto.CouponCreateCommand;
import com.mycouponissuer.coupon_template.domain.Coupon;

public interface CouponFactory {
    public Coupon create(CouponCreateCommand command);
}
