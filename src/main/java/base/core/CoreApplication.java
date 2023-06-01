package base.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import base.core.basic.MyString;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		MyString.stringCompare();
		SpringApplication.run(CoreApplication.class, args);
	}

}