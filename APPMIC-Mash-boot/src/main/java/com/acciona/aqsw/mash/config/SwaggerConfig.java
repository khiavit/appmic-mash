package com.acciona.aqsw.mash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@Configuration
@ComponentScan({ "com.acciona.aqsw.mash.ws.controller", "com.acciona.aqsw.mash.api.service" })
@EnableJpaRepositories("com.acciona.aqsw.mash.model.repository")
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.acciona.aqsw.mash.ws.controller"))
				.paths(PathSelectors.any()).build();
	}

}
