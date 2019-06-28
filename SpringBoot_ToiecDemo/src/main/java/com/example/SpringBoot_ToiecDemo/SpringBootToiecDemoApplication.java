package com.example.SpringBoot_ToiecDemo;

import com.example.SpringBoot_ToiecDemo.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class SpringBootToiecDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootToiecDemoApplication.class, args);
	}

}
