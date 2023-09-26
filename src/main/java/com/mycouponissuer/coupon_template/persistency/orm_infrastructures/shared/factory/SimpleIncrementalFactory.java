package com.mycouponissuer.coupon_template.persistency.orm_infrastructures.shared.factory;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

abstract public class SimpleIncrementalFactory<T> {
    abstract protected List findAll();
    abstract protected Long getIdAsLong(T entity);

    protected long assignId() {
        List<T> all = findAll();

        long maxId = all.stream()
                .mapToLong(entity -> getIdAsLong(entity))
                .max()
                .orElse(0);

        return maxId + 1;
    }
}
