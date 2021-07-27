package com.snkit.datajpa;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@PostMapping(value = "/person",consumes = {"application/json"})
	public Person sayHi(@RequestBody Person person) {
		System.out.println("  Enter into DemoController latency added");
		try {
		Thread.sleep(5000);
		}catch(Exception e) {
			
		}
		System.out.println(" Exit from DemoController");
		return person;
		
	}

}
