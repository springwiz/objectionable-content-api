/**
 *
 */
package com.springwiz.comment.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springwiz.comment.model.Comment;

/**
 * The Class ObjectionableContentVerifier.
 *
 * @author sumit
 */
public class ObjectionableContentVerifier {

	/** The verifier. */
	private static ObjectionableContentVerifier verifier = new ObjectionableContentVerifier();

	/** Logger Instance. */
	private static final Logger logger = LoggerFactory.getLogger("ObjectionableContentController.class");

	/**
	 * Gets the single instance of ObjectionableContentVerifier.
	 *
	 * @return single instance of ObjectionableContentVerifier
	 */
	public static ObjectionableContentVerifier getInstance() {
		return verifier;
	}

	/** The words dictionary. */
	private final Map<String, String> wordsDictionary = new HashMap<>();

	/** The largest word length. */
	private int largestWordLength = 0;

	/**
	 * Instantiates a new objectionable content verifier.
	 */
	private ObjectionableContentVerifier() {
		loadBadwords();
	}

	/**
	 * Iterates over a String input and checks whether a cuss word was found in a
	 * list, then checks if the word should be ignored.
	 *
	 * @param commentInput
	 *            the comment input
	 * @return the array list
	 */
	public ArrayList<String> filterObjectionableComment(Comment commentInput) {
		if (commentInput == null) {
			return new ArrayList<>();
		}
		String input = commentInput.getCommentText();

		final ArrayList<String> badWords = new ArrayList<>();
		input = input.toLowerCase().replaceAll("[^a-zA-Z]", "");

		// iterate over each letter in the word
		for (int start = 0; start < input.length(); start++) {
			// from each letter, keep going to find bad words until either the end of the
			// sentence is reached, or the max word length is reached.
			for (int offset = 1; offset < input.length() + 1 - start && offset < largestWordLength; offset++) {
				final String wordToCheck = input.substring(start, start + offset);

				logger.info(wordToCheck);

				if (wordsDictionary.get(wordToCheck) != null) {
					final String ignore = wordsDictionary.get(wordToCheck);
					if (ignore.equalsIgnoreCase("true")) {
						badWords.add(wordToCheck);
					}
				}
			}
		}
		return badWords;

	}

	/**
	 * Load badwords.
	 */
	public void loadBadwords() {
		try {
			final BufferedReader reader = new BufferedReader(new FileReader("c:\\badwords.csv"));
			String line = "";
			int count = 0;
			while ((line = reader.readLine()) != null) {
				count++;
				String[] content = null;
				try {
					content = line.split(",");
					if (content.length == 0) {
						continue;
					}
					final String word = content[0];
					if (word.length() > largestWordLength) {
						largestWordLength = word.length();
					}
					wordsDictionary.put(word.replaceAll(" ", ""), "true");

				} catch (final Exception e) {
					e.printStackTrace();
				}

			}
			System.out.println("Loaded " + count + " words to filter out");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
