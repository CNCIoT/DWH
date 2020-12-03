package com.stankin.collector;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;


@SpringBootApplication
@EnableSwagger2
//@OpenAPIDefinition(servers = { @Server(url = "https://dwh.kovalev.team"),
//		@Server(url = "http://localhost:5678") })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.stankin.collector.repository")
@EntityScan("com.stankin.collector.domain")
@EnableCaching
@EnableMongoRepositories(basePackages = "com.stankin.collector.mongorepository")
@Slf4j
public class CollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectorApplication.class, args);
	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.parse("20000KB"));
		factory.setMaxRequestSize(DataSize.parse("20000KB"));
		return factory.createMultipartConfig();
	}


	@Bean
	public Docket swaggerDemoApi(){
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.stankin.hackaton"))
				.build();
	}
}
