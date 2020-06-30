package com.iiht.forum.dto;

public class VisitorCommentsDto
{
	private Long commentId;
	private Long postId;
	private String tags;
	private String comment;
	
	public VisitorCommentsDto() {
		super();
	}
	
	public VisitorCommentsDto(Long postId, Long commentId, String tags, String comment) {
		super();
		this.commentId = commentId;
		this.postId = postId;
		this.tags = tags;
		this.comment = comment;
	}

	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}