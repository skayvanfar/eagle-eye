package ir.sk.eagleeye.licenses;

import ir.sk.microservice.utils.UserContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * A Spring Bootstrap class that will be used by Spring Boot to start up and initialize
 * the application
 */
@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
@EnableResourceServer
@ComponentScan({"ir.sk.eagleeye.licenses", "ir.sk.microservice"})
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

/*    @Primary
    @Bean
    @LoadBalanced
    public OAuth2RestTemplate oauth2RestTemplate(@Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext,
                                                 OAuth2ProtectedResourceDetails details) {
        OAuth2RestTemplate template = new OAuth2RestTemplate(details, oauth2ClientContext);

        List interceptors = template.getInterceptors();

        // Adding the UserContextInterceptor to the RestTemplate instance that has been created
        if (interceptors==null){
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        }
        else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }*/

    // The @LoadBalanced annotation tells Spring Cloud to create a Ribbon backed RestTemplate class.
    @Primary
    @LoadBalanced
    @Bean
    public RestTemplate getCustomRestTemplate(){
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();

        // Adding the UserContextInterceptor to the RestTemplate instance that has been created
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
