package ir.sk.eagleeye.authentication.security;

import ir.sk.eagleeye.authentication.model.UserOrganization;
import ir.sk.eagleeye.authentication.repository.OrgUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * Extending a JWT token is easily done by adding a Spring OAuth2 token enhancer
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/25/2020.
 */
public class JWTTokenEnhancer implements TokenEnhancer {

    @Autowired
    private OrgUserRepository orgUserRepo;

    /**
     * looks up the user’s org ID based on their user name
     * @param userName
     * @return
     */
    private String getOrgId(String userName){
        UserOrganization orgUser = orgUserRepo.findByUserName( userName );
        return orgUser.getOrganizationId();
    }

    /**
     * To do this enhancement, you need to add override the enhance() method
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        String orgId =  getOrgId(authentication.getName());

        // All additional attributes are placed in a HashMap and set on the accessToken variable passed into the method
        additionalInfo.put("organizationId", orgId);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
