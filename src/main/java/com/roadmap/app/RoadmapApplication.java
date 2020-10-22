package com.roadmap.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RoadmapApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoadmapApplication.class, args);
	}

}


@RestController
class RoadmapController {
	
	@GetMapping("/employees")
	 public String getEmployees() {  
		return "test";  
	 }

}
