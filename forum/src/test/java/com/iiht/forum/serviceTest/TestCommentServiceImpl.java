package com.iiht.forum.serviceTest;

import static com.forum.ForumProject.UtilTestClass.TestUtils.businessTestFile;
import static com.forum.ForumProject.UtilTestClass.TestUtils.currentTest;
import static com.forum.ForumProject.UtilTestClass.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

//import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.forum.ForumProject.UtilTestClass.MasterData;
import com.forum.forumProject.dao.CommentsDao;
import com.forum.forumProject.entity.Comments;
import com.forum.forumProject.service.CommentService;
import com.forum.forumProject.service.CommentServiceImpl;

public class TestCommentServiceImpl 
{
	@Mock
	private CommentsDao commentsDao;

	@Mock
	private CommentService commentService;
	
	@InjectMocks
	private CommentServiceImpl commentServiceImpl;

	//--------------------------------------------------------------------------------------------------------------------------------
	/*
	 * static { File file = new File("comments_output_revised.txt"); if
	 * (file.exists()) { try { FileUtils.forceDelete(new
	 * File("comments_output_revised.txt")); } catch (IOException e) { // TODO
	 * Auto-generated catch block // e.printStackTrace(); } } }
	 */
	// -------------------------------------------------------------------------------------------------------------------
	@Before
	public void setup() 
	{
		MockitoAnnotations.initMocks(this);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testCommentServiceImplTest() throws Exception 
	{
		boolean value = commentServiceImpl.saveComment(MasterData.getCommentDetails());
		
	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);

	    //File file = new File("comments_output_revised.txt");
	    //FileUtils.write(file, "\ntestCommentServiceImplTest="+(value ? true : false), true);
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testViewAllCommentsImplTest() throws Exception 
	{
		List<Comments> list = new ArrayList<Comments>();
		list.add(MasterData.getCommentDetails());
		list.add(MasterData.getCommentDetails());
		
		when(commentServiceImpl.getAllComments()).thenReturn((List<Comments>) list);
		
		List<Comments> commentFromdb = commentsDao.getAllComments();
	
	    yakshaAssert(currentTest(), commentFromdb==list ? true : false, businessTestFile);

	    //File file = new File("comments_output_revised.txt");
		//FileUtils.write(file, "\ntestViewAllCommentsImplTest="+(commentFromdb==list ? true : false), true); 
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testViewAllCommentsImplTest1() throws Exception
	{
		List<Comments> list = new ArrayList<Comments>();
		  
		when(commentServiceImpl.getAllComments()).thenReturn((List<Comments>) list);
		List<Comments> commentFromdb = commentsDao.getAllComments();

	    yakshaAssert(currentTest(), commentFromdb==list ? true : false, businessTestFile);

	    //File file = new File("comments_output_revised.txt");
		//FileUtils.write(file, "\ntestViewAllCommentsImplTest1="+(commentFromdb==list ? true : false), true); 
	}
	 	
	//--------------------------------------------------------------------------------------------------------------------------------
	
	@Test 
	public void testViewAllCommentsImplTest2() throws Exception 
	{ 
		when(commentsDao.getAllComments()).thenReturn(null);
		  
		List<Comments> commentFromdb = commentServiceImpl.getAllComments();

	    yakshaAssert(currentTest(), commentFromdb==null ? true : false, businessTestFile);

	    //File file = new File("comments_output_revised.txt");
		//FileUtils.write(file, "\ntestViewAllCommentsImplTest2="+(commentFromdb==null ? true : false), true);
	}
	 	
	//--------------------------------------------------------------------------------------------------------------------------------
	@Test 
	public void testSaveCommentsImplTest() throws Exception 
	{
		when(commentService.saveComment(MasterData.getCommentDetails())).thenReturn(true); 
		
		List<Comments> commentFromdb = commentsDao.getAllComments();
		
	    yakshaAssert(currentTest(), commentFromdb != null ? true : false, businessTestFile);

	    //File file = new File("comments_output_revised.txt");
		//FileUtils.write(file, "\ntestSaveCommentsImplTest="+(commentFromdb != null ? true : false), true);
	}
}