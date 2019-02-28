/**
 *
 */
package com.springwiz.comment.model;

/**
 * The Class Comment.
 *
 * @author sumit
 */
public class Comment {

	private String commentText;

	public Comment() {

	}

	public Comment(String commentText) {
		this.commentText = commentText;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
}
