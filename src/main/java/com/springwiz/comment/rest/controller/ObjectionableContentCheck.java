package com.springwiz.comment.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class ObjectionableContentCheck.
 */
@RestController
@RequestMapping("/objectionablecontent")
public class ObjectionableContentCheck {

	/**
	 * Test.
	 *
	 * @return the string
	 */
	@GetMapping("/test")
	public String test() {
		return "Test app";
	}
}