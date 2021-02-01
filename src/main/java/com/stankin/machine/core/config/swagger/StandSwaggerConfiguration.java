package com.stankin.machine.core.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@OpenAPIDefinition(servers = { @Server(url = "https://mdc-api.kovalev.team"),
		@Server(url = "http://localhost:5679") })
public class StandSwaggerConfiguration { }
