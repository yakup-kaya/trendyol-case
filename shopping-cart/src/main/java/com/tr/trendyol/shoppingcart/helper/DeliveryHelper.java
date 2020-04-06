package com.tr.trendyol.shoppingcart.helper;

import com.tr.trendyol.shoppingcart.service.ShoppingCartService;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
public interface DeliveryHelper {

    void calculateFor(ShoppingCartService shoppingCartService);

    long getDeliveryCount(ShoppingCartService shoppingCartService);

}
