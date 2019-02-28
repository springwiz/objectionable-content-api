/**
 *
 */
package com.springwiz.comment.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CommentDto.
 *
 * @author sumit
 */
public class CommentDto {

	private String commentText;

	public CommentDto() {

	}

	public CommentDto(String commentText) {
		this.commentText = commentText;
	}

	@JsonProperty
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
}
