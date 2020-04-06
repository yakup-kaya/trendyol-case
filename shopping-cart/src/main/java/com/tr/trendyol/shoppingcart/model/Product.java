package com.tr.trendyol.shoppingcart.model;

import com.tr.trendyol.core.model.LongBaseDateModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends LongBaseDateModel {

    @OneToOne
    private Category category;

    @Column
    @NonNull
    private Double price;

    @NonNull
    private String title;

}
