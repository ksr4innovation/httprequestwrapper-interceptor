package com.snkit.datajpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatajpaApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DatajpaApplication.class, args);
	}
	
	@Autowired
	EmpRepository empRepository;

	@Override
	public void run(String... args) throws Exception {
	
		
	List<EmpEntity>	 empList =empRepository.findAll();
	
	System.out.println("run    "+empList);
		
	System.out.println("run    "+empList.size());
	}

}
