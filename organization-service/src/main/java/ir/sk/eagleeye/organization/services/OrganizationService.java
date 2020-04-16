package ir.sk.eagleeye.organization.services;

import ir.sk.eagleeye.organization.events.source.SimpleSourceBean;
import ir.sk.eagleeye.organization.model.Organization;
import ir.sk.eagleeye.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Service
public class OrganizationService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);

    @Autowired
    private OrganizationRepository orgRepository;

    @Autowired
    private Tracer tracer;

    @Autowired
    SimpleSourceBean simpleSourceBean;

    public Organization getOrg(String organizationId) {
        Span newSpan = tracer.createSpan("getOrgDBCall");

        logger.debug("In the organizationService.getOrg() call");
        try {
            return orgRepository.findById(organizationId);
        }
        finally{
            newSpan.tag("peer.service", "postgres");
            newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
            tracer.close(newSpan);
        }
    }

    public void saveOrg(Organization org) {
        org.setId(UUID.randomUUID().toString());

        orgRepository.save(org);
        simpleSourceBean.publishOrgChange("SAVE", org.getId());
    }

    public void updateOrg(Organization org) {
        orgRepository.save(org);
        simpleSourceBean.publishOrgChange("UPDATE", org.getId());
    }

    public void deleteOrg(Organization org) {
        orgRepository.delete(org.getId());
        simpleSourceBean.publishOrgChange("DELETE", org.getId());
    }
}
