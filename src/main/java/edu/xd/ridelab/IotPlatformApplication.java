package edu.xd.ridelab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("edu.xd.ridelab.mapper")
public class IotPlatformApplication {
	public static void main(String[] args) {
		SpringApplication.run(IotPlatformApplication.class, args);
	}
}
