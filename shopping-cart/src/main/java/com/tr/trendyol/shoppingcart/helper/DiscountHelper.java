package com.tr.trendyol.shoppingcart.helper;

import com.tr.trendyol.shoppingcart.model.Campaign;
import com.tr.trendyol.shoppingcart.model.Coupon;
import com.tr.trendyol.shoppingcart.service.ShoppingCartService;

import java.util.List;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
public interface DiscountHelper {

    double getCouponDiscountAmount(ShoppingCartService cart, Coupon coupon);

    double getMaxCampaignValue(ShoppingCartService cart, List<Campaign> campaigns);

}
