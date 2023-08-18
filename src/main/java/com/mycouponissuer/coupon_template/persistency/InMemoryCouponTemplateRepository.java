package com.mycouponissuer.coupon_template.persistency;

import com.mycouponissuer.coupon_template.application.outbound_ports.CouponTemplateRepository;
import com.mycouponissuer.coupon_template.domain.CouponTemplate;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponTemplateId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryCouponTemplateRepository implements CouponTemplateRepository {
    private Map<Long, CouponTemplate> repo = new ConcurrentHashMap();
    private AtomicLong idGenerator = new AtomicLong(0);

    public CouponTemplate save(CouponTemplate couponTemplate) {
        couponTemplate.setId(new CouponTemplateId(idGenerator.incrementAndGet()));

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
