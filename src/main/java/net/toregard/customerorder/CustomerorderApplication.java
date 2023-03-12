package net.toregard.customerorder;

import net.toregard.customerorder.domain.MyDataClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class CustomerorderApplication {

	public static void main(String[] args) {
		MyDataClass dataClass = new MyDataClass(UUID.randomUUID(),"Hello");
		System.out.println("Message : Start with "+dataClass.toString());
		SpringApplication.run(CustomerorderApplication.class, args);
	}

}
