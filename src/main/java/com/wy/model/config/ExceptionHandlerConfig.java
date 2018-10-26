package com.wy.model.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ExceptionHandlerConfig {

	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
		return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
			@Override
			public void customize(ConfigurableWebServerFactory factory) {
				factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
				factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/404"));
				factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
				factory.addErrorPages(new ErrorPage(HttpStatus.BAD_GATEWAY, "/error/500"));
				factory.addErrorPages(new ErrorPage(Throwable.class, "/error/500"));
			}
		};
	}

}
