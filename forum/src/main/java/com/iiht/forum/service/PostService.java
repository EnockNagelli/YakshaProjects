package com.iiht.forum.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorPosts;
import com.iiht.forum.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository; 

	@Transactional
	public VisitorPostsDto saveUpdate(VisitorPosts postInput)
	{
		try
		{
			VisitorPosts posts = new VisitorPosts();
			
			posts.setPostId(postInput.getPostId());
			posts.setTitle(postInput.getTitle());
			posts.setTags(postInput.getTags());
			posts.setPostDescription(postInput.getPostDescription());
			
			return getVisitorPostsDto(repository.save(posts));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public VisitorPostsDto getPostById(Long postId)
	{
		return getVisitorPost(repository.findPostById(postId));
	}
	
	public List<VisitorPostsDto> getAllPosts()
	{
		List<VisitorPosts> posts = repository.findAllPosts();
		
		if(CollectionUtils.isEmpty(posts))
		{
			//return throw new RuntimeException("No Records Found");
			//return (List<VisitorPostsDto>) new RuntimeException("No Records Found");
			return null;
		}
		else
		{
			return posts.stream().map(this::getVisitorPostsDto).collect(Collectors.toList());
		}
	}

	public VisitorPostsDto getVisitorPost(VisitorPosts posts)
	{
		return new VisitorPostsDto(posts.getPostId(), posts.getTitle(), posts.getTags(), posts.getPostDescription());
	}
	
	public VisitorPostsDto getVisitorPostsDto(VisitorPosts posts)
	{
		return new VisitorPostsDto(posts.getPostId(), posts.getTitle(), posts.getTags(), posts.getPostDescription());
	}
}
