package ir.sk.eagleeye.licenses;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * A Spring Bootstrap class that will be used by Spring Boot to start up and initialize
 * the application
 */
@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
public class Application {

    // The @LoadBalanced annotation tells Spring Cloud to create a Ribbon backed RestTemplate class.
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
