package com.lakshancd.todo_application_backend.configuration;

import com.lakshancd.todo_application_backend.service.auditor.impl.AuditorServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditorConfig {
    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorServiceImpl();
    }
}
