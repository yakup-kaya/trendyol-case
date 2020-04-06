package com.tr.trendyol.shoppingcart.model;

import com.tr.trendyol.core.model.LongBaseDateModel;
import com.tr.trendyol.shoppingcart.util.DiscountType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Discount extends LongBaseDateModel {

    @Column
    @NonNull
    private Double discountAmount;

    @Column
    @NonNull
    private DiscountType discountType = DiscountType.Amount;

}
