package com.tr.trendyol.shoppingcart.model;

import com.tr.trendyol.core.model.LongBaseDateModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends LongBaseDateModel {

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @Column
    @NonNull
    private String title;

}
