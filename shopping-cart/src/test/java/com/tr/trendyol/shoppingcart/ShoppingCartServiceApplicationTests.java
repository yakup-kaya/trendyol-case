package com.tr.trendyol.shoppingcart;

import com.tr.trendyol.core.constants.Constants;
import com.tr.trendyol.shoppingcart.helper.DeliveryHelper;
import com.tr.trendyol.shoppingcart.helper.impl.DeliveryHelperImpl;
import com.tr.trendyol.shoppingcart.model.Campaign;
import com.tr.trendyol.shoppingcart.model.Category;
import com.tr.trendyol.shoppingcart.model.Coupon;
import com.tr.trendyol.shoppingcart.model.Product;
import com.tr.trendyol.shoppingcart.service.ShoppingCartService;
import com.tr.trendyol.shoppingcart.util.DiscountType;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShoppingCartServiceApplicationTests {

    @Autowired
    private ShoppingCartService testShoppingCartService;

    /**
     * Campaign Discounts are applied first, Then Coupons.
     */
    @Test
    @Order(5)
    public void applyCampaignDiscountFirstTest() {
        Category category = Category.builder().title("Category-1").build();
        Product product = Product.builder().category(category).price(100D).title("Product-1").build();

        Coupon coupon = Coupon.builder().discountType(DiscountType.Rate).minimumPrice(30D).discountAmount(50D).build();
        Campaign campaign1 = Campaign.builder().category(category).discountAmount(50D).discountType(DiscountType.Rate).minItemsCount(9L).build();
        Campaign campaign2 = Campaign.builder().category(category).discountAmount(60D).discountType(DiscountType.Amount).minItemsCount(5L).build();

        testShoppingCartService.addProduct(product, 10);

        testShoppingCartService.applyAllDiscounts(Arrays.asList(campaign1, campaign2), coupon);
        Assert.assertEquals(testShoppingCartService.getTotalAmountAfterDiscounts(), 250d, 0d);
    }

    /**
     * Coupons have minimum amount. If the cart total is less than minimum amount, coupon is not applicable
     * Coupon for 100 TL min purchase amount for a 10% discount
     */
    @Test
    @Order(3)
    public void applyCouponMinPriceTest() {
        Category category = Category.builder().title("Category-1").build();
        Product product = Product.builder().category(category).price(10D).title("Product-1").build();
        Coupon coupon = Coupon.builder().discountType(DiscountType.Rate).minimumPrice(100D).discountAmount(10D).build();
        testShoppingCartService.addProduct(product, 10);
        testShoppingCartService.applyCouponDiscount(coupon);
        Assert.assertEquals(testShoppingCartService.getCouponDiscount(), 0d, 0d);
    }

    @Test
    @Order(4)
    public void applyCouponTest() {
        Category category = Category.builder().title("Category-1").build();
        Product product = Product.builder().category(category).price(10D).title("Product-1").build();
        Coupon coupon = Coupon.builder().discountType(DiscountType.Rate).minimumPrice(80D).discountAmount(50D).build();
        testShoppingCartService.addProduct(product, 10);
        testShoppingCartService.applyCouponDiscount(coupon);
        Assert.assertEquals(testShoppingCartService.getCouponDiscount(), 50d, 0d);

    }

    /**
     * Cart should apply the maximum amount of discount to the cart.
     */
    @Test
    @Order(2)
    public void applyDiscountsTest() {
        Category category = Category.builder().title("Category-1").build();
        Product product = Product.builder().category(category).price(100D).title("Product-1").build();
        Campaign campaign1 = Campaign.builder().category(category).discountAmount(50D).discountType(DiscountType.Rate).minItemsCount(10L).build();
        Campaign campaign2 = Campaign.builder().category(category).discountAmount(60D).discountType(DiscountType.Amount).minItemsCount(5L).build();
        testShoppingCartService.addProduct(product, 18);
        testShoppingCartService.applyCampaignDiscounts(Arrays.asList(campaign1, campaign2));
        Assert.assertEquals(testShoppingCartService.getTotalAmountAfterDiscounts(), 900D, 0D);
    }

    /**
     * NumberOfDeliveries is calculated by the number of distinct categories in the cart.
     * If cart has products that belong to two distinct categories, number of deliveries is 2.
     */
    @Test
    @Order(8)
    public void cartPrintTest() {
        Category category1 = Category.builder().title("Category-1").build();
        Product product1 = Product.builder().category(category1).price(10D).title("Product-1").build();
        testShoppingCartService.addProduct(product1, 10);
        Category category2 = Category.builder().title("Category-2").build();
        Product product2 = Product.builder().category(category2).price(10D).title("Product-2").build();
        testShoppingCartService.addProduct(product2, 10);
        Product product3 = Product.builder().category(category1).price(100D).title("Product-3").build();
        testShoppingCartService.addProduct(product3, 18);
        Product product4 = Product.builder().category(category2).price(100D).title("Product-4").build();
        testShoppingCartService.addProduct(product4, 18);

        Campaign campaign1 = Campaign.builder().category(category1).discountAmount(50D).discountType(DiscountType.Rate).minItemsCount(10L).build();
        Campaign campaign2 = Campaign.builder().category(category2).discountAmount(60D).discountType(DiscountType.Amount).minItemsCount(5L).build();
        testShoppingCartService.applyCampaignDiscounts(Arrays.asList(campaign1, campaign2));

        testShoppingCartService.print();
        Assert.assertTrue(true);
    }

    @Test
    @Order(6)
    public void deliveryCostTest() {
        Category category = Category.builder().title("Category-1").build();
        Product product = Product.builder().category(category).price(10D).title("Product-1").build();
        testShoppingCartService.addProduct(product, 10);
        DeliveryHelper deliveryHelper = new DeliveryHelperImpl(2d, 2d, Constants.FIXED_DELIVERY_COST);
        deliveryHelper.calculateFor(testShoppingCartService);
        Assert.assertEquals(testShoppingCartService.getDeliveryCost(), 6.99, 0d);
    }

    /**
     * NumberOfDeliveries is calculated by the number of distinct categories in the cart.
     * If cart has products that belong to two distinct categories, number of deliveries is 2.
     */
    @Test
    @Order(7)
    public void deliveryNumberTest() {
        Category category1 = Category.builder().title("Category-1").build();
        Product product1 = Product.builder().category(category1).price(10D).title("Product-1").build();
        testShoppingCartService.addProduct(product1, 10);
        Category category2 = Category.builder().title("Category-2").build();
        Product product2 = Product.builder().category(category2).price(10D).title("Product-2").build();
        Product product3 = Product.builder().category(category2).price(10D).title("Product-3").build();
        testShoppingCartService.addProduct(product2, 10);
        testShoppingCartService.addProduct(product3, 10);
        DeliveryHelper deliveryHelper = new DeliveryHelperImpl(2d, 2d, 2.99);
        Assert.assertEquals(deliveryHelper.getDeliveryCount(testShoppingCartService), 2, 0d);
    }

    @AfterEach
    public void runAfterTestMethod() {
        testShoppingCartService.clear();
    }

    @Test
    @Order(1)
    void addProductTest() {
        Category category = Category.builder().title("Category-1").build();
        Product product = Product.builder().category(category).price(100D).title("Product-1").build();
        int productQuantity = 25;
        testShoppingCartService.addProduct(product, productQuantity);
        Assert.assertNotNull(testShoppingCartService);
        Assert.assertTrue(testShoppingCartService.getProducts().containsKey(product));
        Assert.assertEquals((int) testShoppingCartService.getProducts().get(product), productQuantity);
    }


}
