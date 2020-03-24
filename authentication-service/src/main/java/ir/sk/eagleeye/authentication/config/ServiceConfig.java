package ir.sk.eagleeye.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/19/2020.
 */
@Component
//@Configuration
public class ServiceConfig {

    @Value("${signing.key}")
    private String jwtSigningKey="";

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

}
