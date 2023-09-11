package com.mycouponissuer.configurations;

import com.mycouponissuer.coupon_template.persistency.in_memory_infrastructures.coupon.InMemoryCouponFactory;
import com.mycouponissuer.coupon_template.persistency.in_memory_infrastructures.coupon.InMemoryCouponRepository;
import com.mycouponissuer.coupon_template.persistency.in_memory_infrastructures.coupon_template.InMemoryCouponTemplateFactory;
import com.mycouponissuer.coupon_template.persistency.in_memory_infrastructures.coupon_template.InMemoryCouponTemplateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test"})
public class Development {
    @Bean
    public InMemoryCouponTemplateFactory couponTemplateFactory() {
        return new InMemoryCouponTemplateFactory();
    }

    @Bean
    public InMemoryCouponTemplateRepository couponTemplateRepository() {
        return new InMemoryCouponTemplateRepository();
    }

    @Bean
    public InMemoryCouponFactory couponFactory() {
        return new InMemoryCouponFactory();
    }

    @Bean
    public InMemoryCouponRepository couponRepository() {
        return new InMemoryCouponRepository();
    }
}