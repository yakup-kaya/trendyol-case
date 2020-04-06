package com.tr.trendyol.shoppingcart.service;

import com.tr.trendyol.core.service.BaseService;
import com.tr.trendyol.shoppingcart.model.Coupon;
import com.tr.trendyol.shoppingcart.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@Service
@Transactional
public class CouponService extends BaseService<Coupon, Long, CouponRepository> {

    public CouponService(CouponRepository rep) {
        super(rep);
    }
}
