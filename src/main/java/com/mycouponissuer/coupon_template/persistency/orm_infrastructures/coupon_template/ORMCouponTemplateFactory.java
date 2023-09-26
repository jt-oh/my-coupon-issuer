package com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template;

import com.mycouponissuer.coupon_template.application.outbound_ports.CouponTemplateFactory;
import com.mycouponissuer.coupon_template.application.outbound_ports.dto.CouponTemplateCreateCommand;
import com.mycouponissuer.coupon_template.domain.CouponTemplate;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateName;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template.jpa.CouponTemplateJPARepository;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.shared.factory.SimpleIncrementalFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ORMCouponTemplateFactory extends SimpleIncrementalFactory<CouponTemplate> implements CouponTemplateFactory {
    private CouponTemplateJPARepository repository;

    @Autowired
    public ORMCouponTemplateFactory(CouponTemplateJPARepository repository) {
        this.repository = repository;
    }

    public CouponTemplate create(CouponTemplateCreateCommand command) {
        return CouponTemplate.builder()
                .id(new CouponTemplateId(assignId()))
                .name(new CouponTemplateName(command.getName()))
                .quota(command.getQuota())
                .coupons(new ArrayList<CouponId>())
                .build();
    }

    @Override
    protected List findAll() {
        return repository.findAll();
    }

    @Override
    protected Long getIdAsLong(CouponTemplate couponTemplate) {
        return Long.valueOf(couponTemplate.getId().getId());
    }
}
