package com.vn.edu.iuh.fit.cosmetics_bussiness_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.vn.edu.iuh.fit.cosmetics_bussiness_project.repositories")
public class CosmeticsBussinessProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosmeticsBussinessProjectApplication.class, args);
	}

}
