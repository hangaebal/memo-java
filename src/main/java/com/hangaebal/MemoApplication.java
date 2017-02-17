package com.hangaebal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

@EnableCaching
@SpringBootApplication
@PropertySource("config.properties")
public class MemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemoApplication.class, args);
	}

}
