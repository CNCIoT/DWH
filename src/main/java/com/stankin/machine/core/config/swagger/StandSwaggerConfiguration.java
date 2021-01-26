package com.stankin.machine.core.config.swagger;

import org.springframework.context.annotation.Profile;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@Profile("prod")
@OpenAPIDefinition(servers = { @Server(url = "https://dwh.kovalev.team"),
		@Server(url = "http://localhost:5678") })
public class StandSwaggerConfiguration { }
