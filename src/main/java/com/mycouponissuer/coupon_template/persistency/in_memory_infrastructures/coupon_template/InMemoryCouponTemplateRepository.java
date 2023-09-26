package com.mycouponissuer.coupon_template.persistency.in_memory_infrastructures.coupon_template;

import com.mycouponissuer.coupon_template.application.outbound_ports.CouponTemplateRepository;
import com.mycouponissuer.coupon_template.domain.CouponTemplate;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCouponTemplateRepository implements CouponTemplateRepository {
    private Map<Long, CouponTemplate> repo = new ConcurrentHashMap();

    public CouponTemplate save(CouponTemplate couponTemplate) {
        repo.put(Long.valueOf(couponTemplate.getId().getId()), new CouponTemplate(couponTemplate));

        return couponTemplate;
    }

    public ArrayList<CouponTemplate> all() {
        return new ArrayList(repo.values().stream()
                .map(couponTemplate -> new CouponTemplate(couponTemplate))
                .toList());
    }

    public CouponTemplate find(CouponTemplateId id) {
        return new CouponTemplate(repo.get(Long.valueOf(id.getId())));
    }
}
