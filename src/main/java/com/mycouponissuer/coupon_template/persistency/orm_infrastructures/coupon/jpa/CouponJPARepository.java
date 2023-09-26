package com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon.jpa;

import org.springframework.data.repository.ListCrudRepository;

public interface CouponJPARepository extends ListCrudRepository<CouponJPAEntity, Long> {

}
