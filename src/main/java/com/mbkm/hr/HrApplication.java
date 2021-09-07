package com.mbkm.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HrApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
                System.out.println("Server is Running.... !");
	}

}