package com.jrj.spingboot.oauth2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }
    @Bean
    RestTemplate restTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
        ClientHttpRequestFactory requestFactory=new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		//配置日志打印拦截器
		interceptors.add(new LoggingRequestInterceptor());
		oAuth2RestTemplate.setInterceptors(interceptors);
		oAuth2RestTemplate.setRequestFactory(requestFactory);
        return oAuth2RestTemplate;
    }


    protected OAuth2ProtectedResourceDetails resource() {
    	ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        List scopes = new ArrayList<String>(2);
        scopes.add("select");
        resource.setAccessTokenUri("http://localhost:8080/oauth/token");
        resource.setClientId("client_1");
        resource.setClientSecret("123456");
        resource.setScope(scopes);
       // resource.setId("order");
        return resource;
    }
}