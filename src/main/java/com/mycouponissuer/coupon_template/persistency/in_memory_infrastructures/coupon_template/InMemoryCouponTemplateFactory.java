package com.mycouponissuer.coupon_template.persistency.in_memory_infrastructures.coupon_template;

import com.mycouponissuer.coupon_template.application.outbound_ports.CouponTemplateFactory;
import com.mycouponissuer.coupon_template.application.outbound_ports.dto.CouponTemplateCreateCommand;
import com.mycouponissuer.coupon_template.domain.CouponTemplate;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateName;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

//@Component
public class InMemoryCouponTemplateFactory implements CouponTemplateFactory {
    private static AtomicLong idGenerator = new AtomicLong(0);

    public CouponTemplate create(CouponTemplateCreateCommand command) {
        return CouponTemplate.builder()
                .id(new CouponTemplateId(idGenerator.incrementAndGet()))
                .name(new CouponTemplateName(command.getName()))
                .quota(command.getQuota())
                .coupons(new ArrayList<CouponId>())
                .build();
    }
}
