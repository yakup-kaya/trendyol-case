package com.tr.trendyol.shoppingcart.helper.impl;

import com.tr.trendyol.shoppingcart.helper.DiscountHelper;
import com.tr.trendyol.shoppingcart.model.Campaign;
import com.tr.trendyol.shoppingcart.model.Coupon;
import com.tr.trendyol.shoppingcart.model.Product;
import com.tr.trendyol.shoppingcart.service.ShoppingCartService;
import com.tr.trendyol.shoppingcart.util.DiscountType;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
public class DiscountHelperImpl implements DiscountHelper {

    /**
     * Calculates and returns coupon discount amount
     *
     * @param cart   Shopping cart which will be used for calculation
     * @param coupon Applied coupon
     * @return Coupon discount for given shopping cart
     */
    @Override
    public double getCouponDiscountAmount(ShoppingCartService cart, Coupon coupon) {
        double totalPrice = cart.getTotalPrice() - cart.getCampaignDiscount();
        if (totalPrice <= coupon.getMinimumPrice()) {
            log.debug("Coupon discount ignored because total price smaller than coupon minimum price. Total Price: " + totalPrice +
                    ", Coupon Minimum Price: " + coupon.getMinimumPrice());
            return 0;
        }

        double couponDiscountAmount = coupon.getDiscountType().equals(DiscountType.Amount) ?
                coupon.getDiscountAmount() :
                totalPrice * (coupon.getDiscountAmount() / 100);
        log.debug("Total calculated coupon discount: " + couponDiscountAmount);
        return couponDiscountAmount;
    }

    /**
     * Calculate and returns maximum campaign value
     *
     * @param cart      Shopping cart which will be used for calculation
     * @param campaigns Applied campaigns
     * @return Returns maximum discount amount of campaigns
     */
    @Override
    public double getMaxCampaignValue(ShoppingCartService cart, List<Campaign> campaigns) {
        AtomicReference<Double> maxCampaignValue = new AtomicReference<>(0D);
        campaigns.forEach(
                campaign -> {
                    double campaignValue = getCampaignDiscountAmount(cart, campaign);
                    if (campaignValue > maxCampaignValue.get()) {
                        maxCampaignValue.set(campaignValue);
                    }
                }
        );
        log.debug("Maximum campaign value found in shopping cart as {}", maxCampaignValue.get());
        return maxCampaignValue.get();
    }

    /**
     * Returns campaign value for shopping cart
     *
     * @param cart     Shopping cart which will be used for calculation
     * @param campaign Applied campaign
     * @return Returns discount amount of campaign
     */
    private double getCampaignDiscountAmount(ShoppingCartService cart, Campaign campaign) {
        Map<Product, Integer> campaignProducts = new HashMap<>();
        AtomicInteger totalProductAmount = new AtomicInteger(0);
        cart.getProducts().forEach(
                (product, productAmount) -> {
                    if (product.getCategory().equals(campaign.getCategory())) {
                        campaignProducts.put(product, productAmount);
                        totalProductAmount.addAndGet(productAmount);
                    }
                }
        );

        double campaignDiscountAmount = 0D;
        if (totalProductAmount.get() > campaign.getMinItemsCount()) {
            AtomicReference<Double> totalPrice = new AtomicReference<>(0D);
            campaignProducts.forEach((key, value) -> totalPrice.updateAndGet(v -> v + value * key.getPrice()));
            campaignDiscountAmount = campaign.getDiscountType().equals(DiscountType.Amount)
                    ? campaign.getDiscountAmount()
                    : totalPrice.get() * (campaign.getDiscountAmount() / 100);
            log.debug("Total calculated campaign discount: {}", campaignDiscountAmount);
        } else {
            log.debug("Campaign discount ignored because total product amount in campaign categories is smaller than campaign minimum items count. " +
                    "Total Product Amount: {}, Coupon Minimum Price: {}", totalProductAmount.get(), campaign.getMinItemsCount());
        }
        return campaignDiscountAmount;

    }

}
