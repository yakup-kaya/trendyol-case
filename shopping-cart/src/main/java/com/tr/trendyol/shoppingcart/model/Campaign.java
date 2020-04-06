package com.tr.trendyol.shoppingcart.model;

import com.tr.trendyol.shoppingcart.util.DiscountType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Campaign extends Discount {

    @OneToOne
    private Category category;

    @Column
    @NonNull
    private Long minItemsCount = 1L;

    @Builder
    public Campaign(Category category, Long minItemsCount, Double discountAmount, DiscountType discountType) {
        super(discountAmount, discountType);
        this.category = category;
        this.minItemsCount = minItemsCount;
    }

}
