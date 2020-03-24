package ir.sk.eagleeye.authentication.security;

import ir.sk.eagleeye.authentication.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * tell your authentication service how it’s going to generate and translate JWT tokens.
 * is used to define how Spring will manage the creation, signing, and translation of a JWT token.
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/19/2020.
 */
@Configuration
public class JWTTokenStoreConfig {

    @Autowired
    private ServiceConfig serviceConfig;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * Used to read data to and from a token presented to the service
     * @return
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }


    /**
     * Acts as the translator between JWT and OAuth2 server
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // Defines the signing key that will be used to sign a token
        converter.setSigningKey(serviceConfig.getJwtSigningKey()); // TODO: 3/19/2020 setting up JWT using public/private keys
        return converter;
    }


}
