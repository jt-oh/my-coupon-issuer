package com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon;

import com.mycouponissuer.coupon_template.application.outbound_ports.CouponFactory;
import com.mycouponissuer.coupon_template.application.outbound_ports.dto.CouponCreateCommand;
import com.mycouponissuer.coupon_template.domain.Coupon;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon.jpa.CouponJPAEntity;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon.jpa.CouponJPARepository;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.shared.factory.SimpleIncrementalFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//@Component
public class ORMCouponFactory extends SimpleIncrementalFactory<CouponJPAEntity> implements CouponFactory {
    private CouponJPARepository repository;

    @Autowired
    public ORMCouponFactory(CouponJPARepository couponJPARepository) {
        this.repository = couponJPARepository;
    }

    public Coupon create(CouponCreateCommand command) {
        return Coupon.builder()
                .id(new CouponId(new CouponId(assignId())))
                .couponTemplateId(new CouponTemplateId(command.getCouponTemplateId()))
                .ownerId(command.getOwnerId())
                .build();
    }

    @Override
    protected List findAll() {
        return repository.findAll();
    }

    @Override
    protected Long getIdAsLong(CouponJPAEntity couponEntity) {
        return Long.valueOf(couponEntity.getId());
    }
}
