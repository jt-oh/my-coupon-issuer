package com.mycouponissuer.coupon_template.persistency;

import com.mycouponissuer.coupon_template.application.outbound_ports.CouponRepository;
import com.mycouponissuer.coupon_template.domain.Coupon;
import com.mycouponissuer.coupon_template.domain.value_objects.CouponId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryCouponRepository implements CouponRepository {
    private Map<Long, Coupon> repo = new ConcurrentHashMap();
    private AtomicLong idGenerator = new AtomicLong(0);

    public Coupon save(Coupon coupon) {
        coupon.setId(new CouponId(idGenerator.incrementAndGet()));

        repo.put(Long.valueOf(coupon.getId().getId()), new Coupon(coupon));

        return coupon;
    }

    public ArrayList<Coupon> all() {
        return new ArrayList(repo.values().stream()
                .map(coupon -> new Coupon(coupon))
                .toList());
    }

    public Coupon find(CouponId id) {
        return new Coupon(repo.get(Long.valueOf(id.getId())));
    }
}