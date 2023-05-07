package net.toregard.customerorder;

import net.toregard.customerorder.command.CreateOrderCommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.logging.Logger;

@SpringBootApplication
public class CustomerorderApplication {
	private final Logger logger = Logger.getLogger(CustomerorderApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(CustomerorderApplication.class, args);
	}

	@Autowired
	public void configInterceptors(ApplicationContext applicationContext, CommandBus commandBus) {
        commandBus.registerDispatchInterceptor(applicationContext.getBean(CreateOrderCommandInterceptor.class));
        logger.info("ConfigInterceptors "+ CreateOrderCommandInterceptor.class.getSimpleName());
    }

}
