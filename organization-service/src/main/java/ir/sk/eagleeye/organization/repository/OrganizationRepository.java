package ir.sk.eagleeye.organization.repository;

import ir.sk.eagleeye.organization.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String> {

    // @Query("Organization o where o.organizationId='Seattle'")
    Organization findById(String organizationId);
}
