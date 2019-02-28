package com.springwiz.comment.core;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.springwiz.comment.core.ObjectionableContentVerifier;
import com.springwiz.comment.model.Comment;

/**
 * The Class ObjectionableContentVerifierTest.
 */
public class ObjectionableContentVerifierTest {

	private ObjectionableContentVerifier objectionableContentVerifier = null;

	@Before
	public void setUp() throws Exception {
		objectionableContentVerifier = ObjectionableContentVerifier.getInstance();
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test.
	 */
	@Test
	public void testFilterObjectionableComment() {
		final ArrayList<String> wordList = objectionableContentVerifier
				.filterObjectionableComment(new Comment("Your comment is clean"));
		assertTrue(wordList.size() == 0);
	}

	/**
	 * Test.
	 */
	@Test
	public void testFilterObjectionableComment1() {
		final ArrayList<String> wordList = objectionableContentVerifier
				.filterObjectionableComment(new Comment("Your comment is bullshit"));
		assertTrue(wordList.size() > 0);
	}
}
