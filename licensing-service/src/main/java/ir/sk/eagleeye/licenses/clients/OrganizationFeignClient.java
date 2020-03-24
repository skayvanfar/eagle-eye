package ir.sk.eagleeye.licenses.clients;


import ir.sk.eagleeye.licenses.model.Organization;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign—Uses Netflix’s Feign client library to invoke a service via Ribbon
 */
// Identify your service to Feign using the FeignClient Annotation.
@FeignClient("organizationservice")
public interface OrganizationFeignClient {
    // The path and action to your endpoint is defined using the @RequestMapping annotation.
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/v1/organizations/{organizationId}",
            consumes = "application/json")
    // The parameters passed into the endpoint are defined using the @PathVariable endpoint.
    Organization getOrganization(@PathVariable("organizationId") String organizationId);
}
