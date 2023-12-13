package com.teste.totvs.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuração para permitir requisições Cross-Origin Resource Sharing (CORS).
 */
@Configuration
public class WebConfig {

	/**
	 * Configura e registra um filtro CORS para permitir requisições de origens
	 * diferentes.
	 *
	 * @return A configuração do filtro CORS encapsulada em um objeto
	 *         FilterRegistrationBean.
	 */
	@Bean
	FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean() {
		List<String> all = Arrays.asList("*");

		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOriginPatterns(all);
		corsConfiguration.setAllowedHeaders(all);
		corsConfiguration.setAllowedMethods(all);
		corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);

		CorsFilter corsFilter = new CorsFilter(source);

		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsFilter);
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return filter;
	}
}
