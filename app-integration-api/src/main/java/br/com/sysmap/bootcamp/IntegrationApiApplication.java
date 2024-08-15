package br.com.sysmap.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IntegrationApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(IntegrationApiApplication.class, args);
	}

}
