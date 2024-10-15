package com.example.KafkaSpring;

import org.springframework.boot.SpringApplication;

public class TestKafkaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.from(KafkaSpringApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
