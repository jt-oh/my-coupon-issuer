package com.mycouponissuer.coupon_template.application.outbound_ports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CouponCreateCommand {
    private long couponTemplateId;
    private String ownerId;
}
