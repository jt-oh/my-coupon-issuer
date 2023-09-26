package com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template.jpa;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CouponTemplateJPARepository extends ListCrudRepository<CouponTemplateJPAEntity, Long> {

}
