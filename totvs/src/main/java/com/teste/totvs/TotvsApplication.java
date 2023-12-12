package com.teste.totvs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.teste.totvs")
public class TotvsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotvsApplication.class, args);
	}

}
