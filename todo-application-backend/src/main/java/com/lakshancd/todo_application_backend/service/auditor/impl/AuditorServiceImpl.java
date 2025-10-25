package com.lakshancd.todo_application_backend.service.auditor.impl;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
public class AuditorServiceImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("SYSTEM_USER");
    }
}
