package com.iiht.forum.FunctionalTestCases;

import static com.iiht.forum.UtilTestClass.TestUtils.businessTestFile;
import static com.iiht.forum.UtilTestClass.TestUtils.currentTest;
import static com.iiht.forum.UtilTestClass.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.iiht.forum.UtilTestClass.MasterData;
import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorComments;
import com.iiht.forum.model.VisitorPosts;
import com.iiht.forum.service.CommentService;
import com.iiht.forum.service.PostService;

public class TestFunctional 
{	
	@Mock
	private VisitorPostsDto postDto;
	@Mock
	private VisitorCommentsDto commentsDto;

	@Mock
	private VisitorPosts posts;

	@InjectMocks
	private PostService postService;
	@InjectMocks
	private CommentService commentService;
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSavePosts() throws Exception 
	{
		boolean value = postService.savePost(MasterData.getPostDetails());
		
	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);
		
		//File file = new File("output_revised.txt");
	    //FileUtils.write(file, "\ntestSavePosts="+(value ? true : false), true); 
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testSaveComments() throws Exception 
	{
		boolean value = commentService.saveComment(MasterData.getCommentDetails());

	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);

		//File file = new File("output_revised.txt");
	    //FileUtils.write(file, "\ntestSaveComments="+(value ? true : false), true); 
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testViewAllPosts() throws Exception 
	{
		List<VisitorPosts> list = new ArrayList<VisitorPosts>();
		list.add(new VisitorPosts());
		list.add(new VisitorPosts());
	    
	    when(postDto.getAllPosts()).thenReturn((List<VisitorPostsDto>) list);
		List<VisitorPostsDto> postFromdb = postService.getAllPosts();

	    yakshaAssert(currentTest(), postFromdb == list ? true : false, businessTestFile);

	    //File file = new File("output_revised.txt");
		//FileUtils.write(file, "\ntestViewAllPosts="+(postFromdb==list ? true : false), true); 
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testViewAllComments() throws Exception 
	{
		List<VisitorComments> list = new ArrayList<VisitorComments>();
		list.add(new VisitorComments());
		list.add(new VisitorComments());
	    
	    when(commentsDto.getAllComments()).thenReturn((List<VisitorComments>) list);
		List<VisitorCommentsDto> commentFromdb = commentService.getAllComments();

	    yakshaAssert(currentTest(), commentFromdb == list ? true : false, businessTestFile);

	    //File file = new File("output_revised.txt");
		//FileUtils.write(file, "\ntestViewAllComments="+(commentFromdb==list ? true : false), true); 
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testViewAllDiscussions() throws Exception 
	{
		List<VisitorPosts> list = new ArrayList<VisitorPosts>();
		list.add(new VisitorPosts());
		list.add(new VisitorPosts());
	    
	    when(postDto.getAllPosts()).thenReturn((List<VisitorPosts>) list);
		List<VisitorPostsDto> discussionFromdb = postService.getAllPosts();

	    yakshaAssert(currentTest(), discussionFromdb == list ? true : false, businessTestFile);

	    //File file = new File("output_revised.txt");
		//FileUtils.write(file, "\ntestViewAllDiscussions="+(discussionFromdb==list ? true : false), true); 
	}	
}