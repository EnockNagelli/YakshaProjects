package com.iiht.forum.serviceTest;

import static com.iiht.forum.UtilTestClass.TestUtils.businessTestFile;
import static com.iiht.forum.UtilTestClass.TestUtils.currentTest;
import static com.iiht.forum.UtilTestClass.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

//import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.iiht.forum.UtilTestClass.MasterData;
import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorPosts;
import com.iiht.forum.service.PostService;

import junit.framework.Assert;

@RunWith(JUnitRunner.class)
public class TestPostService 
{
	@Mock
    private RestTemplate restTemplate;
	
	@Mock
	private VisitorPostsDto visitorPostDto;

	@InjectMocks
	private PostService postService;

    //----------------------------------------------------------------------------------------------
	/*
	 * static { File file = new File("posts_output_revised.txt"); if (file.exists())
	 * { try { FileUtils.forceDelete(new File("posts_output_revised.txt")); } catch
	 * (IOException e) { // TODO Auto-generated catch block // e.printStackTrace();
	 * } } }
	 */
	// -------------------------------------------------------------------------------------------------------------------
	@Before
	public void setup() 
	{
		MockitoAnnotations.initMocks(this);
	}

	//----------------------------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() 
	{
        VisitorPosts posts = new VisitorPosts((long) 1, "Cricket", "World Sport", "This sport world cup is played every 4 years");
        
        Mockito.when(restTemplate.getForEntity("http://localhost:8766/getPostById/1", VisitorPosts.class)).thenReturn(new ResponseEntity(posts, HttpStatus.OK));
 
        VisitorPostsDto post = postService.getPostById(id);
        Assert.assertEquals(posts, post);
    }
	
	
	
	
	//----------------------------------------------------------------------------------------------
	@Test
	public void testSavePostsImplTest() throws Exception 
	{
		when(postService.savePost(MasterData.getPostDetails())).thenReturn(true);
		
		List<Posts> postFromdb = postDao.getAllPosts();
		
	    yakshaAssert(currentTest(), postFromdb != null ? true : false, businessTestFile);

	    //File file = new File("posts_output_revised.txt");
		//FileUtils.write(file, "\ntestSavePostsImplTest="+(postFromdb != null ? true : false), true);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testPostServiceImplTest() throws Exception 
	{
		boolean value = postServiceImpl.savePost(MasterData.getPostDetails());
		
	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);

	    //File file = new File("posts_output_revised.txt");
	    //FileUtils.write(file, "\ntestPostServiceImplTest="+(value ? true : false), true);
	}

	//--------------------------------------------------------------------------------------------------------------------------------
	@Test
	public void testViewAllPostsImplTest() throws Exception 
	{	
		List<Posts> list = new ArrayList<Posts>();
		list.add(new Posts());
		list.add(new Posts());

		when(postServiceImpl.getAllPosts()).thenReturn((List<Posts>) list);
		
		List<Posts> postFromdb = postDao.getAllPosts();

	    yakshaAssert(currentTest(), postFromdb==list ? true : false, businessTestFile);

	    //File file = new File("posts_output_revised.txt");
		//FileUtils.write(file, "\ntestViewAllPostsImplTest="+(postFromdb==list ? true : false), true);
	}
	
	
	@Test 
	public void testViewAllPostsImplTest1() throws Exception 
	{ 
		List<Posts> list = new ArrayList<Posts>();
	  
		when(postServiceImpl.getAllPosts()).thenReturn((List<Posts>) list);
	  
		List<Posts> postsFromdb = postDao.getAllPosts();
	  
	    yakshaAssert(currentTest(), postsFromdb==list ? true : false, businessTestFile);

	    //File file = new File("posts_output_revised.txt");
		//FileUtils.write(file, "\ntestViewAllPostsImplTest1="+(postsFromdb==list ? true : false), true); 
	}
	  
	@Test 
	public void testViewAllPostsImplTest2() throws Exception 
	{ 
		when(postServiceImpl.getAllPosts()).thenReturn(null);
	  
		List<Posts> postsFromdb = postDao.getAllPosts();
	  
	    yakshaAssert(currentTest(), postsFromdb==null ? true : false, businessTestFile);

	    //File file = new File("posts_output_revised.txt");
		//FileUtils.write(file, "\ntestViewAllPostsImplTest2="+(postsFromdb==null ? true : false), true);
	} 
}