package base.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		SpringApplication.run(CoreApplication.class, args);
	}

}