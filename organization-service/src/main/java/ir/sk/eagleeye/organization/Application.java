package ir.sk.eagleeye.organization;

import ir.sk.microservice.utils.UserContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.servlet.Filter;
@SpringBootApplication
@EnableEurekaClient
//@EnableFeignClients
@EnableCircuitBreaker
// The @EnableResourceServer annotation is used to tell your microservice it’s a protected resource
@EnableResourceServer
@ComponentScan({"ir.sk.eagleeye.organization", "ir.sk.microservice"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Filter userContextFilter() {
        UserContextFilter userContextFilter = new UserContextFilter();
        return userContextFilter;
    }
}
