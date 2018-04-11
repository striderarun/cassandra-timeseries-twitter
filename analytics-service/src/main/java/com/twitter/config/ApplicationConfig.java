package com.twitter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = "com.twitter")
@EnableCassandraRepositories(basePackages = "com.twitter.repository")
@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication
public class ApplicationConfig {

	private  static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfig.class, args);
	}

}
