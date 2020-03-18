package ir.sk.eagleeye.licenses.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/17/2020.
 */
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/licensing/v1/organizations/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }
}
