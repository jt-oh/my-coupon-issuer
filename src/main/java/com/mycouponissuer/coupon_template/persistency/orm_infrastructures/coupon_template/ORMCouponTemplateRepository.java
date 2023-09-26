package com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template;

import com.mycouponissuer.coupon_template.application.outbound_ports.CouponTemplateRepository;
import com.mycouponissuer.coupon_template.domain.CouponTemplate;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateName;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template.jpa.CouponTemplateJPAEntity;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template.jpa.CouponTemplateJPARepository;

import java.util.ArrayList;

public class ORMCouponTemplateRepository implements CouponTemplateRepository {
    private CouponTemplateJPARepository JPARepository;

    public ORMCouponTemplateRepository(CouponTemplateJPARepository JPARepository) {
        this.JPARepository = JPARepository;
    }

    public CouponTemplate save(CouponTemplate couponTemplate) {
        JPARepository.save(CouponTemplateJPAEntity.builder()
                .id(couponTemplate.getId().getId())
                .name(couponTemplate.getName().getName())
                .quota(couponTemplate.getQuota())
                .build()
        );

        return couponTemplate;
    }

    public ArrayList<CouponTemplate> all() {
        return new ArrayList(JPARepository.findAll().stream()
                .map(JPAEntity -> CouponTemplate.builder()
                        .id(new CouponTemplateId(JPAEntity.getId()))
                        .name(new CouponTemplateName(JPAEntity.getName()))
                        .quota(JPAEntity.getQuota())
                        .coupons(new ArrayList(JPAEntity.getCoupons().stream()
                                .map(couponJPAEntity -> new CouponId(couponJPAEntity.getId()))
                                .toList()))
                        .build())
                .toList());
    }

    public CouponTemplate find(CouponTemplateId id) {
        CouponTemplateJPAEntity JPAEntity = JPARepository.findById(id.getId()).orElseThrow();

        return CouponTemplate.builder()
                .id(new CouponTemplateId(JPAEntity.getId()))
                .name(new CouponTemplateName(JPAEntity.getName()))
                .quota(JPAEntity.getQuota())
                .coupons(new ArrayList(JPAEntity.getCoupons().stream()
                        .map(couponJPAEntity -> new CouponId(couponJPAEntity.getId()))
                        .toList()))
                .build();
    }
}
