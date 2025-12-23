package com.ssafy.tigetting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ssafy.tigetting")
public class TigettingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TigettingApplication.class, args);
	}

}
