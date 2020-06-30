package com.iiht.forum.FunctionalTestCases;

import static com.iiht.forum.UtilTestClass.TestUtils.businessTestFile;
import static com.iiht.forum.UtilTestClass.TestUtils.currentTest;
import static com.iiht.forum.UtilTestClass.TestUtils.yakshaAssert;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.iiht.forum.UtilTestClass.MasterData;
import com.iiht.forum.controller.VisitorPostController;
import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorPosts;
import com.iiht.forum.service.CommentService;
import com.iiht.forum.service.PostService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@SpringBootConfiguration
@SpringBootTest
public class TestController 
{
	@Mock
	private PostService postService;

	@Mock
	private CommentService commentService;

	@InjectMocks
	private VisitorPostController visitorPostController;

	private MockMvc mockMvc;

	// -------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	@Test
	public void testAllPosts() throws IOException 
	{
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8766/getAllPosts", String.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseJson = objectMapper.readTree(response.getBody());

		assertThat(responseJson.isMissingNode(), is(false));
		assertThat(responseJson.toString(), equalTo("[]"));
	}

	// -------------------------------------------------------------------------------------------------------------------
	/*
	 * Description : This test is to perform setup for Mockito initiations
	 */
	@Before public void setup() throws Exception 
	{
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(visitorPostController).build(); 
	}
	  
	  //-----------------------------------------------------------------------------
	  /*
	   * Description : This test is to perform Loading the landing page URL
	   */
//	  @Test public void testLoadingPageUrl() throws Exception { MvcResult result =
//	  this.mockMvc.perform(get("/")).andExpect(view().name("visitor")).andReturn();
	  
//	  yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false, businessTestFile); }
	  
	//-----------------------------------------------------------------------------
	/*
	 * Description : This test is to perform add new post in the Forum
	 */
	@Test public void testAddPost() throws Exception 
	{ 
		MvcResult result = this.mockMvc.perform(get("/savePost")).andExpect(status().isOk()).andExpect(view().name("addPost")).andReturn();
	  
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false, businessTestFile); 
	}
	  
	//-----------------------------------------------------------------------------
	/*
	 * Description : This test is to perform view all the posts from database
	 */
	@Test public void testViewAllPosts() throws Exception 
	{ 
		List<VisitorPostsDto> posts = new ArrayList<VisitorPostsDto>(); posts.add(new VisitorPostsDto()); posts.add(new VisitorPostsDto());
	  
		when(postService.getAllPosts()).thenReturn((List<VisitorPostsDto>) posts);
	  
		MvcResult result = this.mockMvc.perform(get("/getAllPosts")).andExpect(status().isOk());	//.andExpect(view().name("viewPosts")).andReturn();
	  
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false, businessTestFile); 
	}
	  
	//-----------------------------------------------------------------------------
	/*
	 * Description : This test is to perform check the null operation against view all posts operation
	 */
	@Test 
	public void testViewAllPostsCase() throws Exception 
	{
		when(postService.getAllPosts()).thenReturn(null);
	  
		MvcResult result = this.mockMvc.perform(get("/getAllPosts")).andExpect(status().isOk());		//.andExpect(view().name("visitor")).andReturn();
	  
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false, businessTestFile); 
	}
	  
	//---------------------------------------------------------------------------
	/*
	 * Description : This test is to perform add a comment and check the status of the operation
	 */
	@Test 
	public void testAddComments() throws Exception 
	{ 
		  MvcResult result = this.mockMvc.perform(get("/saveComment?id=1")).andExpect(status().isOk());	//.andExpect(view().name("addComment")).andReturn();
	  
		  yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false, businessTestFile); 
	}
	  
	//---------------------------------------------------------------------------
	/*
	 * Description : This test is to perform to view all the discussions of the posts
	 */
	@Test 
	public void testViewAllDiscussions() throws Exception 
	{ 
		List<Posts> discuss = new ArrayList<Posts>(); discuss.add(new Posts()); discuss.add(new Posts());
	  
		when(postService.getAllPosts()).thenReturn((List<Posts>) discuss);
	  
		MvcResult result = this.mockMvc.perform(get("/viewDiscussion")).andExpect(status().isOk());	//.andExpect(view().name("viewDiscussionList")).andReturn();
	  
		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false, businessTestFile); 
	}
	  
	//---------------------------------------------------------------------------	  
	/*
	 * Description : This test is to perform add a comment and check the status of the operation
     */
	@Test 
	public void testViewAllDiscussionsCase() throws Exception 
	{
		when(postService.getAllPosts()).thenReturn(null);
  
		MvcResult result = this.mockMvc.perform(get("/viewDiscussion")).andExpect(status().isOk());	//.andExpect(view().name("visitor")).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == HttpStatus.OK.value() ? true : false, businessTestFile); 
	}
}