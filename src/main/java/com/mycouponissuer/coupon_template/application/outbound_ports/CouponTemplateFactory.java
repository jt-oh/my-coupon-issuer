package com.mycouponissuer.coupon_template.application.outbound_ports;

import com.mycouponissuer.coupon_template.application.outbound_ports.dto.CouponTemplateCreateCommand;
import com.mycouponissuer.coupon_template.domain.CouponTemplate;

public interface CouponTemplateFactory {
    public CouponTemplate create(CouponTemplateCreateCommand command);
}
