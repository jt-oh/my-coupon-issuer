package com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon.jpa;

import com.mycouponissuer.coupon_template.persistency.orm_infrastructures.coupon_template.jpa.CouponTemplateJPAEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CouponJPAEntity {
    @Id
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "coupon_template_id")
    private CouponTemplateJPAEntity couponTemplate;
    @Column(name = "owner_id")
    private String ownerId;
}
