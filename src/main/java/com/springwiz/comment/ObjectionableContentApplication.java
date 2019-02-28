package com.springwiz.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Class ObjectionableContentApplication.
 */
@SpringBootApplication
@ComponentScan("com.springwiz.comment")
public class ObjectionableContentApplication {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ObjectionableContentApplication.class, args);
	}
}