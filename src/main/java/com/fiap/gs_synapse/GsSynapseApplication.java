package com.fiap.gs_synapse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GsSynapseApplication {
	public static void main(String[] args) {
		SpringApplication.run(GsSynapseApplication.class, args);
	}
}
