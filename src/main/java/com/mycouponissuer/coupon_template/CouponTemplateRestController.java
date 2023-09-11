package com.mycouponissuer.coupon_template;

import com.mycouponissuer.coupon_template.application.CouponTemplateApplicationService;
import com.mycouponissuer.coupon_template.application.dto.CouponDTO;
import com.mycouponissuer.coupon_template.application.dto.CouponIssueRequest;
import com.mycouponissuer.coupon_template.application.dto.CouponTemplateCreateRequest;
import com.mycouponissuer.coupon_template.application.dto.CouponTemplateDTO;
import com.mycouponissuer.coupon_template.application.outbound_ports.CouponRepository;
import com.mycouponissuer.coupon_template.application.outbound_ports.CouponTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("api/v1/coupon-template")
public class CouponTemplateRestController {
    @Autowired
    private CouponTemplateApplicationService couponTemplateApplicationService;
    @Autowired
    private CouponTemplateRepository couponTemplateRepository;
    @Autowired
    private CouponRepository couponRepository;

    @PostMapping
    public CouponTemplateDTO createCouponTemplate(@RequestBody CouponTemplateCreateRequest createRequest) {
        return couponTemplateApplicationService.createCouponTemplate(createRequest);
    }

    @GetMapping
    public ArrayList<CouponTemplateDTO> getCouponTemplateList() {
        return couponTemplateApplicationService.getCouponTemplateList();
    }

    @GetMapping("/{id}")
    public CouponTemplateDTO getCouponTemplate(@PathVariable long id) {
        return couponTemplateApplicationService.getCouponTemplate(id);
    }

    @PostMapping("/{id}/issue")
    public CouponDTO issueCoupon(@PathVariable long id, @RequestBody String userId) throws Exception {
    // TODO: handle exceptionㄹ
        return couponTemplateApplicationService.issueCoupon(
            CouponIssueRequest.builder()
                .couponTemplateId(id)
                .ownerId(userId)
                .build()
        );
    }
}
