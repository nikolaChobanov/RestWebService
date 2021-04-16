package com.spring.rest.vmtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VmtaskApplication {

    private static final Logger logger = LoggerFactory.getLogger(VmtaskApplication.class);

	public static void main(String[] args) {

	    logger.info("Starting the application");

    SpringApplication.run(VmtaskApplication.class, args);
	}

}
