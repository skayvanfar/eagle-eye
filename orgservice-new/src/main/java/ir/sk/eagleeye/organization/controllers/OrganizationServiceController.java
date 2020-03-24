package ir.sk.eagleeye.organization.controllers;

import ir.sk.eagleeye.organization.model.Organization;
import ir.sk.eagleeye.organization.services.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/14/2020.
 */

@RestController
@RequestMapping(value = "v1/organizations")
public class OrganizationServiceController {

    @Autowired
    private OrganizationService orgService;

    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceController.class);

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        logger.debug("Looking up data for org {}", organizationId);

        Organization org = orgService.getOrg(organizationId);
        org.setContactName("NEW::" + org.getContactName());
        return org;
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.PUT)
    public void updateOrganization(@PathVariable("organizationId") String orgId, @RequestBody Organization org) {
        orgService.updateOrg(org);
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.POST)
    public void saveOrganization(@RequestBody Organization org) {
        orgService.saveOrg(org);
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable("orgId") String orgId, @RequestBody Organization org) {
        orgService.deleteOrg(org);
    }
}
