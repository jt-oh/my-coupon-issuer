package com.mycouponissuer.coupon_template;

import com.mycouponissuer.coupon_template.application.CouponTemplateApplicationService;
import com.mycouponissuer.coupon_template.application.dto.CouponDTO;
import com.mycouponissuer.coupon_template.application.dto.CouponIssueRequest;
import com.mycouponissuer.coupon_template.application.dto.CouponTemplateCreateRequest;
import com.mycouponissuer.coupon_template.application.dto.CouponTemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("api/v1/coupon-template")
public class CouponTemplateRestController {
    // ToDo: 실행 환경 설정에 따라 Dependency Injection 시 Persistency 를 설정 가능하도록 기능화
    @Autowired
    private CouponTemplateApplicationService couponTemplateApplicationService;

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
        // TODO: handle exception
        return couponTemplateApplicationService.issueCoupon(
            CouponIssueRequest.builder()
                .couponTemplateId(id)
                .ownerId(userId)
                .build()
        );
    }
}
