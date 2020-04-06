package com.tr.trendyol.shoppingcart.init;

import com.tr.trendyol.shoppingcart.model.Campaign;
import com.tr.trendyol.shoppingcart.model.Category;
import com.tr.trendyol.shoppingcart.model.Coupon;
import com.tr.trendyol.shoppingcart.model.Product;
import com.tr.trendyol.shoppingcart.service.CampaignService;
import com.tr.trendyol.shoppingcart.service.CategoryService;
import com.tr.trendyol.shoppingcart.service.CouponService;
import com.tr.trendyol.shoppingcart.service.ProductService;
import com.tr.trendyol.shoppingcart.util.DiscountType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    private CampaignService campaignService;

    private Map<String, Category> categories = new HashMap<>();

    private CategoryService categoryService;

    private CouponService couponService;

    private ProductService productService;

    @Autowired
    public DataInitializer(ProductService productService, CategoryService categoryService, CampaignService campaignService, CouponService couponService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.campaignService = campaignService;
        this.couponService = couponService;
    }

    @Override
    public void run(String... args) {
        log.debug("All data initialization started");
        initCategories();
        initProducts();
        initCampaigns();
        initCoupons();
        log.debug("All data initialization finished");
    }

    private void initCategories() {
        log.debug("Categories initialization started");
        Category food = Category.builder()
                .title("food").build();
        categoryService.save(food);
        Category nuts = Category.builder()
                .parent(food)
                .title("nuts").build();
        categoryService.save(nuts);
        Category vegetables = Category.builder()
                .parent(food)
                .title("vegetables").build();
        categoryService.save(vegetables);
        categories.put("food", food);
        categories.put("nuts", nuts);
        categories.put("vegetables", vegetables);
        log.debug("Categories initialization finished");
    }

    private void initProducts() {
        log.debug("Products initialization started");
        Product product = Product.builder()
                .title("Apple")
                .price(100.0)
                .category(categories.get("food")).build();
        productService.save(product);

        product = Product.builder()
                .title("Almonds")
                .price(150.0)
                .category(categories.get("vegetables")).build();

        productService.save(product);
        log.debug("Products initialization finished");
    }

    private void initCampaigns() {
        log.debug("Campaigns initialization started");
        List<Campaign> campaignList = new ArrayList<>();
        Campaign campaign1 = Campaign.builder()
                .category(categories.get("vegetables"))
                .minItemsCount(3L)
                .discountAmount(20.0)
                .discountType(DiscountType.Rate).build();
        campaignList.add(campaign1);

        Campaign campaign2 = Campaign.builder()
                .category(categories.get("vegetables"))
                .minItemsCount(5L)
                .discountAmount(50.0)
                .discountType(DiscountType.Rate).build();
        campaignList.add(campaign2);

        Campaign campaign3 = Campaign.builder()
                .category(categories.get("nuts"))
                .minItemsCount(5L)
                .discountAmount(5.0)
                .discountType(DiscountType.Amount).build();
        campaignList.add(campaign3);

        campaignService.save(campaignList);
        log.debug("Campaigns initialization finished");
    }

    private void initCoupons() {
        log.debug("Coupons initialization started");
        Coupon coupon = Coupon.builder()
                .minimumPrice(100.0)
                .discountAmount(10.0)
                .discountType(DiscountType.Rate).build();
        couponService.save(coupon);
        log.debug("Coupons initialization finished");
    }

}
