package com.mycouponissuer.configurations;

import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon.ORMCouponFactory;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon.ORMCouponRepository;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon.jpa.CouponJPARepository;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template.ORMCouponTemplateFactory;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template.ORMCouponTemplateRepository;
import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template.jpa.CouponTemplateJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class Production {
    @Bean
    @Autowired
    public ORMCouponTemplateFactory couponTemplateFactory(CouponTemplateJPARepository repository) {
        return new ORMCouponTemplateFactory(repository);
    }

    @Bean
    @Autowired
    public ORMCouponTemplateRepository couponTemplateRepository(CouponTemplateJPARepository repository) {
        return new ORMCouponTemplateRepository(repository);
    }

    @Bean
    @Autowired
    public ORMCouponFactory couponFactory(CouponJPARepository repository) {
        return new ORMCouponFactory(repository);
    }

    @Bean
    @Autowired
    public ORMCouponRepository couponRepository(CouponJPARepository repository) {
        return new ORMCouponRepository(repository);
    }
}