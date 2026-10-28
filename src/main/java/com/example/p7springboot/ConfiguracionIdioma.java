package com.example.p7springboot;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

// Configuración del idioma: define cuál es el inicial y cómo se cambia.
@Configuration
public class ConfiguracionIdioma implements WebMvcConfigurer {

    // Recuerda el idioma elegido durante la sesión (sin base de datos).
    // Si todavía no se ha elegido ninguno, se usa español.
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolvedor = new SessionLocaleResolver();
        resolvedor.setDefaultLocale(Locale.forLanguageTag("es"));
        return resolvedor;
    }

    // Cambia el idioma cuando la URL lleva el parámetro lang (?lang=es o ?lang=en).
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    // Activa el interceptor para que se aplique en todas las peticiones.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
