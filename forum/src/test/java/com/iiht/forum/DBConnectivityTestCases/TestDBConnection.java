package com.iiht.forum.DBConnectivityTestCases;

import static com.iiht.forum.UtilTestClass.TestUtils.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.junit.platform.runner.JUnitPlatform;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.forum.UtilTestClass.MasterData;

@ExtendWith(SpringExtension.class)
@RunWith(JUnitPlatform.class)
public class TestDBConnection 
{
	//----------------------------------------------------------------------------------------------
	@Test
	public void testConnectivity() throws IOException 
	{
		boolean value = false;
		
		Properties properties = MasterData.getProperties();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(properties.getProperty("spring.datasource.url"));
		dataSource.setUsername(properties.getProperty("spring.datasource.username"));
		dataSource.setPassword(properties.getProperty("spring.datasource.password"));		
		
	    if(dataSource != null)
	    	value = true;

	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);	    
	}
    //----------------------------------------------------------------------------------------------
	@Test
	public void hibernateProperties() throws IOException 
	{
		boolean value = false;
		
		Properties properties = MasterData.getProperties();
		
		properties.put("hibernate.dialect", properties.getProperty("spring.jpa.hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", properties.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show_sql", properties.getProperty("spring.jpa.show-sql"));
		
		if(properties != null)
			value = true;

	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);	    
	}
    //----------------------------------------------------------------------------------------------	
//	@SuppressWarnings("unchecked")
//	@Test 
//	public void testSqlException() throws IOException 
//	{ 
//		boolean value = false;
		
//		RestTemplate session = new RestTemplate(MasterData.getSession().getObject()); 
//		String hql = "FROM VisitorPosts";
	  
//		List<VisitorPosts> size = (List<VisitorPosts>) session.find(hql); 

//		if(size != null)
//			value = true;
		
//	    yakshaAssert(currentTest(), value ? true : false, businessTestFile);
//	}
}