package com.mycouponissuer.coupon_template.application;

import com.mycouponissuer.coupon_template.application.dto.CouponDTO;
import com.mycouponissuer.coupon_template.application.dto.CouponIssueRequest;
import com.mycouponissuer.coupon_template.application.dto.CouponTemplateCreateRequest;
import com.mycouponissuer.coupon_template.application.dto.CouponTemplateDTO;
import com.mycouponissuer.coupon_template.application.outbound_ports.CouponRepository;
import com.mycouponissuer.coupon_template.application.outbound_ports.CouponTemplateRepository;
import com.mycouponissuer.coupon_template.domain.Coupon;
import com.mycouponissuer.coupon_template.domain.CouponTemplate;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CouponTemplateApplicationServiceImpl implements CouponTemplateApplicationService {
    private CouponTemplateRepository couponTemplateRepository;
    private CouponRepository couponRepository;

    @Autowired
    CouponTemplateApplicationServiceImpl(CouponTemplateRepository couponTemplateRepository, CouponRepository couponRepository) {
        this.couponTemplateRepository = couponTemplateRepository;
        this.couponRepository = couponRepository;
    }


    // needs Transactional
    public CouponTemplateDTO createCouponTemplate(CouponTemplateCreateRequest createRequest) {
         CouponTemplate couponTemplate = couponTemplateRepository.save(
             CouponTemplate.builder()
                .name(new CouponTemplateName(createRequest.getName()))
                .quota(createRequest.getQuota())
                .build()
         );

        return new CouponTemplateDTO(couponTemplate);
    }

    public ArrayList<CouponTemplateDTO> getCouponTemplateList() {
        return new ArrayList(
            couponTemplateRepository.all()
                .stream()
                .map(couponTemplate -> new CouponTemplateDTO(couponTemplate))
                .toList()
        );
    }

    public CouponTemplateDTO getCouponTemplate(long id) {
        return new CouponTemplateDTO(couponTemplateRepository.find(new CouponTemplateId(id)));
    }

    // needs Transactional
    public CouponDTO issueCoupon(CouponIssueRequest couponIssueRequest) throws Exception {
        CouponTemplate couponTemplate = couponTemplateRepository.find(
                new CouponTemplateId(couponIssueRequest.getCouponTemplateId()));

        Coupon coupon;
        try {
            coupon = couponTemplate.issueCoupon(couponIssueRequest.getOwnerId());
        } catch (Exception e) {
            throw new Exception("CouponTemplate is not issuable");
        }

        couponRepository.save(coupon);

        return new CouponDTO(coupon);
    }
}
