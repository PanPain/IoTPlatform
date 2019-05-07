package edu.xd.ridelab;

import edu.xd.ridelab.heartbeat.HeartBeatReceiver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("edu.xd.ridelab.mapper")
public class IotPlatformApplication {
	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(IotPlatformApplication.class, args);
		HeartBeatReceiver heartBeatReceiver = app.getBean(HeartBeatReceiver.class);
		heartBeatReceiver.receiveHeartBeat(19030);
	}
}
