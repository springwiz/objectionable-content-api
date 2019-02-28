package com.springwiz.comment.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springwiz.comment.core.ObjectionableContentVerifier;
import com.springwiz.comment.model.Comment;
import com.springwiz.comment.rest.model.ApiError;
import com.springwiz.comment.rest.model.ApiSuccess;
import com.springwiz.comment.rest.model.CommentDto;
import com.springwiz.comment.rest.model.mapper.ValueObjectMapper;

/**
 * The Class ObjectionableContentController.
 *
 * @author sumit REST End Point for the Objectionable Content Api
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/objectionablecontent")
public class ObjectionableContentController {

	/** Logger Instance. */
	private static final Logger logger = LoggerFactory.getLogger("ObjectionableContentController.class");

	/** The value object mapper. */
	@Autowired
	private ValueObjectMapper valueObjectMapper;

	/**
	 * Verify content.
	 *
	 * @param comment
	 *            CommentDto
	 * @return the response entity
	 * @api {get} /objectionablecontent/verify
	 * @apiName verifyContent
	 * @apiGroup objectionablecontent
	 * @apiParam (Object) {Object} commentDto CommentDto
	 * @apiSuccess {Boolean} success success.
	 * @apiError {json} Error-Response: { "status": "error" "message": "" }
	 */
	@PostMapping("/verify")
	public ResponseEntity<Object> verifyContent(@RequestBody CommentDto comment) {
		// verify comment
		logger.info("\n**Entry: verifyContent**" + " comment: " + comment.getCommentText());

		try {
			final Comment input = valueObjectMapper.map(comment, Comment.class);
			final List<String> badWordList = ObjectionableContentVerifier.getInstance()
					.filterObjectionableComment(input);
			if (badWordList.size() > 0) {
				return new ResponseEntity<Object>(
						new ApiSuccess("RESP0001", ApiSuccess.ResponseStatus.SUCCESS, "Bad words found"),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(
						new ApiSuccess("RESP0002", ApiSuccess.ResponseStatus.SUCCESS, "No bad words found"),
						HttpStatus.OK);
			}
		} catch (final Exception e) {
			return new ResponseEntity<Object>(new ApiError(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}