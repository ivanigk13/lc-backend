package com.lawencon.community.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.PathItem.HttpMethod;
import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class ObjectConfig {

	@Bean("inittable")
	public SpringLiquibase liquibaseTable(@Autowired DataSource dataSource) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:db/migration/script/init_table.sql");
		liquibase.setDataSource(dataSource);
		return liquibase;
	}
	
	@Bean
	@DependsOn("inittable")
	public SpringLiquibase liquibaseData(@Autowired DataSource dataSource) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:db/migration/script/init_data.sql");
		liquibase.setDataSource(dataSource);
		return liquibase;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods(
								HttpMethod.POST.name(), 
								HttpMethod.GET.name(),
								HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());
			}
		}; 
	};
	
}
