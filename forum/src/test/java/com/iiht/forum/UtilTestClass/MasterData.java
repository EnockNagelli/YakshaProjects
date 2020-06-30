package com.iiht.forum.UtilTestClass;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.iiht.forum.dto.VisitorCommentsDto;
import com.iiht.forum.dto.VisitorPostsDto;
import com.iiht.forum.model.VisitorComments;
import com.iiht.forum.model.VisitorPosts;

public class MasterData 
{
	public static VisitorPostsDto getPostDetails() 
	{
		VisitorPostsDto post = new VisitorPostsDto();
		
		post.setPostId(post.getPostId());
		post.setTitle("Spring Technology");
		post.setTags("Java");
		post.setPostDescription("Used in IT Sector");
		
		return post;
	}
	
	public static VisitorCommentsDto getCommentDetails() 
	{
		VisitorCommentsDto comments = new VisitorCommentsDto();
		
		comments.setPostId(comments.getPostId());
		comments.setCommentId(comments.getCommentId());
		comments.setTags("I Like it");
		comments.setComment("Design Patterns are important in Java Technology");
		
		return comments;
	}
	
	public static Properties getProperties() throws IOException 
	{
		FileReader reader = new FileReader("src\\main\\resources\\application.properties");
		Properties properties = new Properties();
		properties.load(reader);
		return properties;
	}
	
	public static LocalSessionFactoryBean getSession() throws IOException 
	{
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		Properties properties = getProperties();
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(properties.getProperty("spring.datasource.url"));
		dataSource.setUsername(properties.getProperty("spring.datasource.username"));
		dataSource.setPassword(properties.getProperty("spring.datasource.password"));
		
		lsfb.setDataSource(dataSource);
		lsfb.setPackagesToScan("com.iiht.forum.model");
		
		properties.put("hibernate.dialect", properties.getProperty("spring.jpa.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", properties.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show_sql", properties.getProperty("spring.jpa.show-sql"));
		
		lsfb.setHibernateProperties(properties);
		
		try {
			lsfb.afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lsfb;
	}
}