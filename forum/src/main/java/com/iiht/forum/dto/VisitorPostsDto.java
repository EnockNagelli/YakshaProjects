package com.iiht.forum.dto;

public class VisitorPostsDto {

	private Long postId;
	private String title;
	private String tags;
	private String postDescription;
 
	public VisitorPostsDto() {
		super();
	}
	
	public VisitorPostsDto(Long postId, String title, String tags, String postDescription) {
		super();
		this.postId = postId;
		this.title = title;
		this.tags = tags;
		this.postDescription = postDescription;
	}

	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPostDescription() {
		return postDescription;
	}
	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}
	
	@Override
	public String toString() {
		return "VisitorPostsDto [postId=" + postId + ", title=" + title + "]";
	}
}