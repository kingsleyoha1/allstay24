package com.kingsleyohas.allstay24backoffice;

import com.kingsleyohas.allstay24backoffice.entity.PropertyType;
import com.kingsleyohas.allstay24backoffice.repository.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AllStay24Application implements CommandLineRunner {
	@Autowired
	private PropertyTypeRepository propertyTypeRepository;

	public static void main(String[] args) {
		SpringApplication.run(AllStay24Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Add property types here
		PropertyType propertyType1 = new PropertyType("Apartment");
		PropertyType propertyType2 = new PropertyType("House");
		PropertyType propertyType3 = new PropertyType("Condo");

		// Save to database
		propertyTypeRepository.save(propertyType1);
		propertyTypeRepository.save(propertyType2);
		propertyTypeRepository.save(propertyType3);

		// Optionally log to the console
		System.out.println("Property Types added to the databases.");
	}


}
