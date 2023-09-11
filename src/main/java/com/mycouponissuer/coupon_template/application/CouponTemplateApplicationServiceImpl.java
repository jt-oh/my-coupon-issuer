package com.mycouponissuer.coupon_template.application;

import com.mycouponissuer.coupon_template.application.dto.CouponDTO;
import com.mycouponissuer.coupon_template.application.dto.CouponIssueRequest;
import com.mycouponissuer.coupon_template.application.dto.CouponTemplateCreateRequest;
import com.mycouponissuer.coupon_template.application.dto.CouponTemplateDTO;
import com.mycouponissuer.coupon_template.application.outbound_ports.CouponFactory;
import com.mycouponissuer.coupon_template.application.outbound_ports.CouponRepository;
import com.mycouponissuer.coupon_template.application.outbound_ports.CouponTemplateFactory;
import com.mycouponissuer.coupon_template.application.outbound_ports.CouponTemplateRepository;
import com.mycouponissuer.coupon_template.application.outbound_ports.dto.CouponCreateCommand;
import com.mycouponissuer.coupon_template.application.outbound_ports.dto.CouponTemplateCreateCommand;
import com.mycouponissuer.coupon_template.domain.Coupon;
import com.mycouponissuer.coupon_template.domain.CouponTemplate;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CouponTemplateApplicationServiceImpl implements CouponTemplateApplicationService {
    private CouponTemplateRepository couponTemplateRepository;
    private CouponTemplateFactory couponTemplateFactory;
    private CouponRepository couponRepository;
    private CouponFactory couponFactory;

    @Autowired
    CouponTemplateApplicationServiceImpl(
            CouponTemplateRepository couponTemplateRepository,
            CouponTemplateFactory couponTemplateFactory,
            CouponRepository couponRepository,
            CouponFactory couponFactory
    ) {
        this.couponTemplateRepository = couponTemplateRepository;
        this.couponRepository = couponRepository;
        this.couponTemplateFactory = couponTemplateFactory;
        this.couponFactory = couponFactory;
    }

    public CouponTemplateDTO createCouponTemplate(CouponTemplateCreateRequest createRequest) {
        CouponTemplate couponTemplate = couponTemplateFactory.create(
                CouponTemplateCreateCommand.builder()
                        .name(createRequest.getName())
                        .quota(createRequest.getQuota())
                        .build()
        );

        return new CouponTemplateDTO(couponTemplateRepository.save(couponTemplate));
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

    public CouponDTO issueCoupon(CouponIssueRequest couponIssueRequest) throws Exception {
        // needs Mutual Exclusion
        // start MutEx
        CouponTemplate couponTemplate = couponTemplateRepository.find(
                new CouponTemplateId(couponIssueRequest.getCouponTemplateId()));

        Coupon issuedCoupon = couponFactory.create(
                CouponCreateCommand.builder()
                        .couponTemplateId(couponTemplate.getId().getId())
                        .ownerId(couponIssueRequest.getOwnerId())
                        .build()
        );

        try {
            couponTemplate.addIssuedCoupon(issuedCoupon);
        } catch (Exception e) {
            throw e;
        }

        CouponDTO couponDTO = new CouponDTO(couponRepository.save(issuedCoupon));
        // end MutEx

        return couponDTO;
    }
}
