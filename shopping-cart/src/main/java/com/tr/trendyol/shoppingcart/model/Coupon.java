package com.tr.trendyol.shoppingcart.model;

import com.tr.trendyol.shoppingcart.util.DiscountType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Coupon extends Discount {

    @Column
    @NonNull
    private Double minimumPrice;

    @Builder
    public Coupon(Double minimumPrice, Double discountAmount, DiscountType discountType) {
        super(discountAmount, discountType);
        this.minimumPrice = minimumPrice;
    }

}
