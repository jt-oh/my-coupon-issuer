package com.mycouponissuer.coupon_template.application.outbound_ports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CouponTemplateCreateCommand {
    private String name;
    private String ownerId;
    private int quota;
}
