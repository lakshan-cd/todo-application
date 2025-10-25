package com.lakshancd.todo_application_backend.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration class for defining beans.
 */
@Configuration
public class BeanConfiguration {
    /**
     * Defines a bean for the ModelMapper.
     *
     * @return ModelMapper bean instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
