package com.iiht.forum.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.model.VisitorComments;
import com.iiht.forum.repository.CommentRepository;

@Service
public class CommentService 
{
	@Autowired
	private CommentRepository commentRepo; 

	@Transactional
	public VisitorCommentsDto saveUpdate(VisitorComments commentInput)
	{
		try
		{
			VisitorComments comments = new VisitorComments();
			
			comments.setCommentId(commentInput.getCommentId());
			comments.setPostId(commentInput.getPostId());
			comments.setTags(commentInput.getTags());
			comments.setComment(commentInput.getComment());
			
			return getVisitorCommentsDto(commentRepo.save(comments));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//public VisitorCommentsDto getById(Long postId)
	//{
	//	return getVisitorCommentsDto(commentRepo.getOne(postId));
	//}
	
	public List<VisitorCommentsDto> getCommentById(Long postId)
	{
		List<VisitorComments> posts = commentRepo.findVisitorByPostId(postId);
		
		if(CollectionUtils.isEmpty(posts))
		{
			//return throw new RuntimeException("No Records Found");
			//return (List<VisitorCommentsDto>) new RuntimeException("No Records Found");
			return null;
		}
		else
		{
			return posts.stream().map(this::getVisitorCommentsDto).collect(Collectors.toList());
		}
	}

	public VisitorCommentsDto getVisitorCommentsDto(VisitorComments comments)
	{
		return new VisitorCommentsDto(comments.getPostId(), comments.getCommentId(), comments.getTags(), comments.getComment());
	}	
}
