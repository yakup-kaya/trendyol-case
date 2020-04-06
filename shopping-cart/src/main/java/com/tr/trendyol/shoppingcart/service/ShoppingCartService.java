package com.tr.trendyol.shoppingcart.service;

import com.tr.trendyol.shoppingcart.model.Campaign;
import com.tr.trendyol.shoppingcart.model.Coupon;
import com.tr.trendyol.shoppingcart.model.Product;

import java.util.List;
import java.util.Map;
/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
public interface ShoppingCartService {

    void addProduct(Product product, Integer quantity);

    void applyAllDiscounts(List<Campaign> campaigns, Coupon coupon);

    void applyCampaignDiscounts(List<Campaign> campaigns);

    void applyCouponDiscount(Coupon coupon);

    void clear();

    void print();

    double getCampaignDiscount();

    double getCouponDiscount();

    double getDeliveryCost();

    void setDeliveryCost(double cost);

    Map<Product, Integer> getProducts();

    double getTotalAmountAfterDiscounts();

    double getTotalPrice();

}
