package com.tr.trendyol.shoppingcart.repository;

import com.tr.trendyol.core.repository.BaseRepository;
import com.tr.trendyol.shoppingcart.model.Coupon;
import org.springframework.stereotype.Repository;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Repository
public interface CouponRepository extends BaseRepository<Coupon, Long> {

}
