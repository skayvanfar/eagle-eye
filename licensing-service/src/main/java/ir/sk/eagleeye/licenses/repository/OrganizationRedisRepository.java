package ir.sk.eagleeye.licenses.repository;

import ir.sk.eagleeye.licenses.model.Organization;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/26/2020.
 */
public interface OrganizationRedisRepository {
    void saveOrganization(Organization org);
    void updateOrganization(Organization org);
    void deleteOrganization(String organizationId);
    Organization findOrganization(String organizationId);
}
