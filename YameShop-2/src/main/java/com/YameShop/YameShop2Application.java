package com.YameShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class YameShop2Application {

	public static void main(String[] args) {
		SpringApplication.run(YameShop2Application.class, args);
	}

}
