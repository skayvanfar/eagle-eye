package ir.sk.eagleeye.organization.services;

import ir.sk.eagleeye.organization.model.Organization;
import ir.sk.eagleeye.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/14/2020.
 */
@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository orgRepository;

    public Organization getOrg(String organizationId) {
        return orgRepository.findById(organizationId);
    }

    public void saveOrg(Organization org) {
        org.setId(UUID.randomUUID().toString());

        orgRepository.save(org);

    }

    public void updateOrg(Organization org) {
        orgRepository.save(org);
    }

    public void deleteOrg(Organization org) {
        orgRepository.delete(org.getId());
    }
}
