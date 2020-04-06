package com.tr.trendyol.shoppingcart.service.impl;

import com.tr.trendyol.shoppingcart.helper.DiscountHelper;
import com.tr.trendyol.shoppingcart.helper.impl.DiscountHelperImpl;
import com.tr.trendyol.shoppingcart.model.Campaign;
import com.tr.trendyol.shoppingcart.model.Category;
import com.tr.trendyol.shoppingcart.model.Coupon;
import com.tr.trendyol.shoppingcart.model.Product;
import com.tr.trendyol.shoppingcart.service.ShoppingCartService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Data
@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    /**
     * Campaign discount in shopping cart
     */
    private double campaignDiscount;

    /**
     * Coupon discount in shopping cart
     */
    private double couponDiscount;

    /**
     * Delivery cost in shopping cart
     */
    private double deliveryCost;

    /**
     * Holds all products in shopping cart
     */
    private Map<Product, Integer> products = new HashMap<>();

    /**
     * Total amount of all products those are in shopping cart without discounts
     */
    private double totalPrice;

    public DiscountHelper discountHelper=new DiscountHelperImpl();

    @Override
    public void addProduct(Product product, Integer quantity) {

        if (quantity == 0) {
            log.debug("Product {} removed from shopping card because quantity is zero", product.getTitle());
            products.remove(product);
        } else {
            if (products.containsKey(product)) {
                quantity += products.get(product);
            }
            log.debug("Product {} added in shopping card. Total Quantity: {}", product.getTitle(), quantity);
            products.put(product, quantity);
        }

        products.forEach((key, value) -> totalPrice += value * key.getPrice());
        log.debug("Total price calculates as {} without any discount", totalPrice);

    }

    @Override
    public void applyAllDiscounts(List<Campaign> campaigns, Coupon coupon) {
        applyCampaignDiscounts(campaigns);
        applyCouponDiscount(coupon);
    }

    @Override
    public void applyCampaignDiscounts(List<Campaign> campaigns) {
        campaignDiscount = discountHelper.getMaxCampaignValue(this, campaigns);
        log.debug("Campaign discount calculated as {}", campaignDiscount);
    }

    @Override
    public void applyCouponDiscount(Coupon coupon) {
        couponDiscount = discountHelper.getCouponDiscountAmount(this, coupon);
        log.debug("Coupon discount calculated as {}", couponDiscount);
    }

    @Override
    public void clear() {
        products.clear();
        totalPrice = 0;
        campaignDiscount = 0;
        couponDiscount = 0;
        deliveryCost = 0;
        discountHelper = new DiscountHelperImpl();
        log.debug("Shopping cart products are cleared");
    }

    /**
     * Group Products by Category and Print the CategoryName, ProductName, Quantity, Unit Price, Total Price,
     * Total Discount(coupon,campaign) applied
     */
    @Override
    public void print() {
        Map<Category, List<Product>> groupByCategory = new HashMap<>();

        for (Map.Entry<Product, Integer> productQuantityEntry : products.entrySet()) {
            Product product = productQuantityEntry.getKey();
            if (groupByCategory.containsKey(product.getCategory())) {
                groupByCategory.get(product.getCategory()).add(product);
            } else {
                groupByCategory.put(product.getCategory(), new ArrayList<>(Collections.singletonList(product)));
            }
        }

        groupByCategory.forEach(
                (category, productList) -> {
                    log.info("Category Name: {}", category.getTitle());
                    log.info("Product List Under {} Category", category.getTitle());
                    productList.forEach(
                            product -> log.info("      * Product Name : {}, Quantity: {}, Unit Price: {}, Total Price: {}", product.getTitle(), products.get(product), product.getPrice(), products.get(product) * product.getPrice())
                    );
                }
        );
        log.info("Total Applied Discount: {}", getTotalDiscount());

    }

    @Override
    public double getTotalAmountAfterDiscounts() {
        double totalAmountAfterDiscount = totalPrice - getTotalDiscount();
        log.debug("Total shopping cart amount after discount: {}", totalAmountAfterDiscount);
        return totalAmountAfterDiscount;
    }

    private double getTotalDiscount() {
        return campaignDiscount + couponDiscount;
    }

}
