package ir.sk.eagleeye.authentication.repository;

import ir.sk.eagleeye.authentication.model.UserOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/25/2020.
 */
@Repository
public interface OrgUserRepository extends CrudRepository<UserOrganization,String> {
    public UserOrganization findByUserName(String userName);
}
