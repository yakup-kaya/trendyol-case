package com.tr.trendyol.shoppingcart.controller;

import com.tr.trendyol.core.repository.CrudResource;
import com.tr.trendyol.shoppingcart.model.Coupon;
import com.tr.trendyol.shoppingcart.repository.CouponRepository;
import com.tr.trendyol.shoppingcart.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@RestController
@RequestMapping(CouponController.URL)
public class CouponController extends CrudResource<Coupon, Long, CouponRepository, CouponService> {

    protected static final String URL = "/api/v1/coupons";

    @Override
    protected String getEntityName() {
        return "coupon";
    }

    @Override
    protected String getBaseUrl() {
        return CouponController.URL;
    }

}
