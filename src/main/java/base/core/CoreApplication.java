package base.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		System.out.println("Hello World!");

		int [] nums = {2, 7, 11, 15};

		System.out.println(Arrays.toString(nums));

		SpringApplication.run(CoreApplication.class, args);
	}

}