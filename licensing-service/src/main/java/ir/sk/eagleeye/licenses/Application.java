package ir.sk.eagleeye.licenses;

import ir.sk.eagleeye.licenses.config.ServiceConfig;
import ir.sk.eagleeye.licenses.events.models.OrganizationChangeModel;
import ir.sk.microservice.utils.UserContextInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
@EnableBinding(Sink.class) // The @EnableBinding annotation tells the service to the use the channels defined in the Sink interface to listen for incoming messages
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private ServiceConfig serviceConfig;

    /**
     * defined in them so all transactions will be traced with Zipkin
     * @return
     */
    @Bean
    public Sampler defaultSampler() {
        return new AlwaysSampler();
    }

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

    /**
     * sets up the actual database connection to the Redis server
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
        jedisConnFactory.setHostName( serviceConfig.getRedisServer());
        jedisConnFactory.setPort( serviceConfig.getRedisPort() );
        return jedisConnFactory;
    }

    /**
     * creates a RedisTemplate that will be used to carry out actions against your Redis server
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

/*    @StreamListener(Sink.INPUT)
    public void loggerSink(OrganizationChangeModel orgChange) {
        logger.debug("Received an event for organization id {}", orgChange.getOrganizationId());
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
