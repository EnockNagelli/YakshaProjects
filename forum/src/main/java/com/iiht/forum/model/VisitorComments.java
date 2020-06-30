package com.iiht.forum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class VisitorComments 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commentid")
	private Long commentId;
	
	@Column(name = "postid")
	private Long postId;

	@Column(name = "tags")
	private String tags;
	
	@Column(name = "comment")
	private String comment;
	
	//@OneToMany(mappedBy="VistiorPosts",cascade=CascadeType.PERSIST)
	//private List<VisitorPosts> posts;

	public VisitorComments() {
		super();
	}
	
	public VisitorComments(Long postId, Long commentId, String tags, String comment) {
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