package ir.sk.eagleeye.organization.repository;

import ir.sk.eagleeye.organization.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/14/2020.
 */
@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String> {
    public Organization findById(String organizationId);
}
