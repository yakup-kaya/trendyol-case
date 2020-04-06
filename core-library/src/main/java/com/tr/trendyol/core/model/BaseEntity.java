package com.tr.trendyol.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class BaseEntity<ID extends Serializable> extends BaseDateModel<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private ID id;

}
