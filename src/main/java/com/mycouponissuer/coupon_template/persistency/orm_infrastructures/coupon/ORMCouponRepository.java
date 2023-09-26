package com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon;

import com.mycouponissuer.coupon_template.application.outbound_ports.CouponRepository;
import com.mycouponissuer.coupon_template.domain.Coupon;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon.jpa.CouponJPAEntity;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon.jpa.CouponJPARepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ORMCouponRepository implements CouponRepository {
    private CouponJPARepository JPARepository;

    @Autowired
    public ORMCouponRepository(CouponJPARepository couponJPARepository) {
        this.JPARepository = couponJPARepository;
    }
    public Coupon save(Coupon coupon) {
        JPARepository.save(CouponJPAEntity.builder()
                .id(coupon.getId().getId())
                .couponTemplate(coupon.getCouponTemplateId().getId())
                .ownerId(coupon.getOwnerId())
                .build()
        );

        return coupon;
    }

    public ArrayList<Coupon> all() {
        return new ArrayList (
                JPARepository.findAll().stream()
                    .map(couponJPAEntity -> Coupon.builder()
                            .id(new CouponId(couponJPAEntity.getId()))
                            .couponTemplateId(new CouponTemplateId(couponJPAEntity.getCouponTemplate().getId()))
                            .ownerId(couponJPAEntity.getOwnerId())
                            .build()
                    )
                    .toList()
        );
    }

    public Coupon find(CouponId id) {
        CouponJPAEntity couponJPAEntity = JPARepository.findById(id.getId()).orElseThrow();

        return Coupon.builder()
                .id(new CouponId(couponJPAEntity.getId()))
                .couponTemplateId(new CouponTemplateId(couponJPAEntity.getCouponTemplateId()))
                .ownerId(couponJPAEntity.getOwnerId())
                .build();
    }
}
