package com.jrj.spingboot.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes={ServiceRibbonApplication.class})
@TestComponent
//@EnableOAuth2Client
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HelloServiceTest {

	@Autowired
	private HelloService helloService;
	@Test
	public void test(){
		String s=helloService.hiService("");
		System.out.println(s);;
	}
}
