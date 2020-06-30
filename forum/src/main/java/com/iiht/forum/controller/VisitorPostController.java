package com.iiht.forum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorPosts;
import com.iiht.forum.service.PostService;

@RestController
public class VisitorPostController {

	@Autowired
	private PostService postService;

	@RequestMapping("/")
	public String home() {
		return "Welcome to Online Forum Application...";
	}
	
	@PostMapping(value="/savePost")
	public VisitorPostsDto saveUpdate(@RequestBody VisitorPosts visitorPosts) {
		return postService.saveUpdate(visitorPosts);
	}
	
	@GetMapping(value = "/getPostById/{id}")
	public VisitorPostsDto getVisitorByPostId(@PathVariable Long id) {
		return postService.getPostById(id);
	}
	
	@GetMapping(value = "/getAllPosts", produces = "application/json")
	public List<VisitorPostsDto> getAllVisitorPosts() {
		return postService.getAllPosts();
	}
	
	@GetMapping(value = "/getDiscussionList")
	public Map<Long, String> getAllDiscussions() {
		HashMap<Long, String> discussion = null;
		try	{
			discussion = new HashMap<Long, String>();
			List<VisitorPostsDto> posts = postService.getAllPosts();
			if(!CollectionUtils.isEmpty(posts)) {
				for(VisitorPostsDto p : posts) {
					discussion.put(p.getPostId(), p.getTitle());
				}
			}
			else {
				throw new RuntimeException("No Records Found");
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return discussion;
	}
}