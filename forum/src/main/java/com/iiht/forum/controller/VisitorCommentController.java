package com.iiht.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.model.VisitorComments;
import com.iiht.forum.service.CommentService;

@RestController
public class VisitorCommentController 
{
	@Autowired
	private CommentService commentService;

	@PostMapping(value="/postComment")
	public VisitorCommentsDto saveUpdate(@RequestBody VisitorComments visitorComments)
	{
		return commentService.saveUpdate(visitorComments);
	}
	
	@GetMapping(value = "/getCommentByPostId/{id}")
	public List<VisitorCommentsDto> getVisitorByCommentId(@PathVariable Long id)
	{
		return commentService.getCommentById(id);
	}
}