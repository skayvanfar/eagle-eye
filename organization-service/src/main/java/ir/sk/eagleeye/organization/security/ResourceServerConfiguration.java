package ir.sk.eagleeye.organization.security;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * he access control rules around the service.
 * To define access control rules.
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/15/2020.
 */
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * All the access rules are defined inside the overridden configure() method
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception{
        /*All access rules are configured
        off the HttpSecurity object
        passed into the method*/
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/v1/organizations/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();
    }
}
