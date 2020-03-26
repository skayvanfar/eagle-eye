package ir.sk.eagleeye.licenses.clients;

import ir.sk.eagleeye.licenses.model.Organization;
import ir.sk.eagleeye.licenses.repository.OrganizationRedisRepository;
import ir.sk.microservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Rest—Uses an enhanced Spring RestTemplate to invoke the Ribbon-based service
 */
@Component
public class OrganizationRestTemplateClient {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);

    /*@Autowired
    OAuth2RestTemplate restTemplate;*/

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrganizationRedisRepository orgRedisRepo;

    private Organization checkRedisCache(String organizationId) {
        try {
            return orgRedisRepo.findOrganization(organizationId);
        }
        catch (Exception ex){
            logger.error("Error encountered while trying to retrieve organization {} check Redis Cache.  Exception {}", organizationId, ex);
            return null;
        }
    }

    private void cacheOrganizationObject(Organization org) {
        try {
            orgRedisRepo.saveOrganization(org);
        }catch (Exception ex){
            logger.error("Unable to cache organization {} in Redis. Exception {}", org.getId(), ex);
        }
    }

    public Organization getOrganization(String organizationId) {
        logger.debug(">>> In Licensing Service.getOrganization: {}. Thread Id: {}", UserContextHolder.getContext().getCorrelationId(), Thread.currentThread().getId());
        logger.debug("In Licensing Service.getOrganization: Authorization {}", UserContextHolder.getContext().getAuthorization());

        Organization org = checkRedisCache(organizationId);

        if (org!=null){
            logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId, org);
            return org;
        }

        logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);


        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://zuulservice/api/organization/v1/organizations/{organizationId}", // When using a Ribbon-back RestTemplate, you build the target URL with the Eureka service ID.
                        HttpMethod.GET,
                        null, Organization.class, organizationId);

        /*Save the record from cache*/
        org = restExchange.getBody();

        if (org!=null) {
            cacheOrganizationObject(org);
        }

        return org;
    }
}
