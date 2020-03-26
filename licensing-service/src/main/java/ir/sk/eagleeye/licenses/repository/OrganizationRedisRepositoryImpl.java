package ir.sk.eagleeye.licenses.repository;

import ir.sk.eagleeye.licenses.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/26/2020.
 */
@Repository
public class OrganizationRedisRepositoryImpl implements OrganizationRedisRepository {

    // The name of the hash in your Redis server where organization data is stored
    private static final String HASH_NAME ="organization";

    private RedisTemplate<String, Organization> redisTemplate;

    // The HashOperations class is a set of data is stored Spring helper methods for carrying out data operations on the Redis server
    private HashOperations hashOperations;

    public OrganizationRedisRepositoryImpl(){
        super();
    }

    @Autowired
    private OrganizationRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void saveOrganization(Organization org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void updateOrganization(Organization org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void deleteOrganization(String organizationId) {
        hashOperations.delete(HASH_NAME, organizationId);
    }

    @Override
    public Organization findOrganization(String organizationId) {
        return (Organization) hashOperations.get(HASH_NAME, organizationId);
    }
}
