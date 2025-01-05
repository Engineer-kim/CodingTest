package com.brick.codingtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.brick.codingtest.Solution2.PinPong;

@SpringBootApplication
public class CodingtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingtestApplication.class, args);
		PinPong.startServer();
	}

}
