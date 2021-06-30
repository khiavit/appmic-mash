package com.acciona.aqsw.mash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ApplicationMash {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMash.class, args);
	}

}
