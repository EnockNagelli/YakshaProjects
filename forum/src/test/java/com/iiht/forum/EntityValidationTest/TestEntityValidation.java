package com.iiht.forum.EntityValidationTest;

import static com.iiht.forum.UtilTestClass.TestUtils.businessTestFile;
import static com.iiht.forum.UtilTestClass.TestUtils.currentTest;
import static com.iiht.forum.UtilTestClass.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.forum.UtilTestClass.MasterData;
import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.dto.VisitorPostsDto;

import org.junit.platform.runner.JUnitPlatform;

@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
public class TestEntityValidation 
{
    private Validator validator;

    //----------------------------------------------------------------------------------------------
    @BeforeEach
    public void setUp() throws IOException
    {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostSuccess() throws IOException
    {
    	VisitorPostsDto posts = MasterData.getPostDetails();
        Set<ConstraintViolation<VisitorPostsDto>> violations = validator.validate(posts);
        
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, businessTestFile);

        //File file = new File("entity_output_revised.txt");
	    //FileUtils.write(file, "\ntestPostSuccess="+(violations.isEmpty() ? true : false), true);
    }
    
    //----------------------------------------------------------------------------------------------
    @Test
    public void testPostFailed() throws IOException
    {
    	VisitorPostsDto posts = MasterData.getPostDetails();
    	posts.setPostDescription(null);
        Set<ConstraintViolation<VisitorPostsDto>> violations = validator.validate(posts);
        
	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, businessTestFile);
    }

    //----------------------------------------------------------------------------------------------
    @Test
    public void testCommentSuccess() throws IOException
    {
    	VisitorCommentsDto comments = MasterData.getCommentDetails();
        Set<ConstraintViolation<VisitorCommentsDto>> violations = validator.validate(comments);

	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, businessTestFile);
    }
    
    @Test
    public void testCommentFailed() throws IOException
    {
    	VisitorCommentsDto comments = MasterData.getCommentDetails();
    	comments.setComment(null);
        Set<ConstraintViolation<VisitorCommentsDto>> violations = validator.validate(comments);

	    yakshaAssert(currentTest(), violations.isEmpty() ? true : false, businessTestFile);
    }
}