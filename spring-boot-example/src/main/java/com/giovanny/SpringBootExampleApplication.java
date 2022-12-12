package com.giovanny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController // makes the class to serve rest endpoints (received as JSON)
public class SpringBootExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExampleApplication.class, args);
	}

	@GetMapping("/greet") // GET Request
	public GreetResponse greet() {
		GreetResponse greetResponse = new GreetResponse(
				"Hello World!",
				List.of("Java", "JavaScript", "C#"),
				new Person("Giovanny", 20, 100.00));
		return greetResponse;
	}

	record Person(String name, int age, double savings) {}

	record GreetResponse(
			String greet,
			List<String> favProgrammingLanguages,
			Person person
	) {}

}
