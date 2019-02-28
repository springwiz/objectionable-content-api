package com.springwiz.comment.core.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springwiz.comment.rest.controller.ObjectionableContentController;
import com.springwiz.comment.rest.model.CommentDto;

/**
 * The Class ObjectionableContentControllerTest.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ObjectionableContentController.class, secure = false)
public class ObjectionableContentControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test verify objectionable content controller.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testVerifyObjectionableContentController() throws Exception {
		final CommentDto comment = new CommentDto("Your comment is bullshit");
		final String commentJson = new ObjectMapper().writeValueAsString(comment);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/objectionablecontent/verify")
				.accept(MediaType.APPLICATION_JSON).content(commentJson).contentType(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		final MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertTrue(response.getContentAsString().contains("Bad words found"));
	}

	/**
	 * Test verify objectionable content controller 1.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testVerifyObjectionableContentController1() throws Exception {
		final CommentDto comment = new CommentDto("Your comment is clean");
		final String commentJson = new ObjectMapper().writeValueAsString(comment);

		final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/objectionablecontent/verify")
				.accept(MediaType.APPLICATION_JSON).content(commentJson).contentType(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		final MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertTrue(response.getContentAsString().contains("No bad words found"));
	}
}