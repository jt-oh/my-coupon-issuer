package com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template.jpa;

import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon.jpa.CouponJPAEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CouponTemplateJPAEntity {
    @Id
    private Long id;
    private String name;
    private Integer quota;
    @OneToMany(targetEntity = CouponJPAEntity.class, mappedBy = "couponTemplate")
    private ArrayList<CouponJPAEntity> coupons;
}
