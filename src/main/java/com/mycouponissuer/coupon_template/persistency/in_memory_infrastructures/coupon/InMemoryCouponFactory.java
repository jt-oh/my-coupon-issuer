package com.mycouponissuer.coupon_template.persistency.in_memory_infrastructures.coupon;

import com.mycouponissuer.coupon_template.application.outbound_ports.CouponFactory;
import com.mycouponissuer.coupon_template.application.outbound_ports.dto.CouponCreateCommand;
import com.mycouponissuer.coupon_template.domain.Coupon;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryCouponFactory implements CouponFactory {
    private static AtomicLong idGenerator = new AtomicLong(0);

    public Coupon create(CouponCreateCommand command) {
        return Coupon.builder()
                .id(new CouponId(idGenerator.incrementAndGet()))
                .couponTemplateId(new CouponTemplateId(command.getCouponTemplateId()))
                .ownerId(command.getOwnerId())
                .build();
    }
}
