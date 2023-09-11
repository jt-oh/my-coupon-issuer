package com.mycouponissuer.coupon_template.application;

import com.mycouponissuer.coupon_template.application.dto.CouponDTO;
import com.mycouponissuer.coupon_template.application.dto.CouponIssueRequest;
import com.mycouponissuer.coupon_template.application.dto.CouponTemplateCreateRequest;
import com.mycouponissuer.coupon_template.application.dto.CouponTemplateDTO;

import java.util.ArrayList;

public interface CouponTemplateApplicationService {
    public CouponTemplateDTO createCouponTemplate(CouponTemplateCreateRequest createRequest);
    public ArrayList<CouponTemplateDTO> getCouponTemplateList();
    public CouponTemplateDTO getCouponTemplate(long id);
    public CouponDTO issueCoupon(CouponIssueRequest couponIssueRequest) throws Exception;
}
