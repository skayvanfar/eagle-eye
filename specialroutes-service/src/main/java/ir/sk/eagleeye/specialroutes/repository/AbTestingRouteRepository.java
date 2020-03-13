package ir.sk.eagleeye.specialroutes.repository;

import ir.sk.eagleeye.specialroutes.model.AbTestingRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/14/2020.
 */
@Repository
public interface AbTestingRouteRepository extends CrudRepository<AbTestingRoute,String> {
    public AbTestingRoute findByServiceName(String serviceName);
}
