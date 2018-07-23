package com.aerohive.nms.a3.communicator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class A3Communicator extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(A3Communicator.class);
    }

    public static void main(final String[] args) throws Exception {
        SpringApplication.run(A3Communicator.class, args);
    }
}
