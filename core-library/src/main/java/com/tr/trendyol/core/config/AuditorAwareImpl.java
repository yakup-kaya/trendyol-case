package com.tr.trendyol.core.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */

class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("user-1");
    }
}
