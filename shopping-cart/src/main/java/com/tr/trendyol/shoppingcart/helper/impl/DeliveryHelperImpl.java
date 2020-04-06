package com.tr.trendyol.shoppingcart.helper.impl;

import com.tr.trendyol.shoppingcart.helper.DeliveryHelper;
import com.tr.trendyol.shoppingcart.model.Category;
import com.tr.trendyol.shoppingcart.model.Product;
import com.tr.trendyol.shoppingcart.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Data
@AllArgsConstructor
public class DeliveryHelperImpl implements DeliveryHelper {

    /**
     * Holds cost for each delivery
     */
    private double costPerDelivery;

    /**
     * Holds cost for each product
     */
    private double costPerProduct;

    /**
     * Holds fixed deliver cost
     */
    private double fixedCost;

    /**
     * Calculates total cost
     *
     * @param shoppingCartService Shopping cart that holds added products
     */
    @Override
    public void calculateFor(ShoppingCartService shoppingCartService) {
        int numberOfProducts = shoppingCartService.getProducts().size();
        double totalDeliveryCost = costPerDelivery * getDeliveryCount(shoppingCartService);
        totalDeliveryCost += costPerProduct * numberOfProducts;
        totalDeliveryCost += fixedCost;
        shoppingCartService.setDeliveryCost(totalDeliveryCost);
    }

    /**
     * Returns total delivery count for given shopping cart
     *
     * @param shoppingCartService Shopping cart that holds added products
     * @return delivery count
     */
    @Override
    public long getDeliveryCount(ShoppingCartService shoppingCartService) {
        Set<Category> distinctCategories = new HashSet<>();
        for (Product product : shoppingCartService.getProducts().keySet()) {
            distinctCategories.add(product.getCategory());
        }
        return distinctCategories.size();
    }


}
