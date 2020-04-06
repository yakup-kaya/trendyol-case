package com.tr.trendyol.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private String message;

    private Date timestamp;

}
