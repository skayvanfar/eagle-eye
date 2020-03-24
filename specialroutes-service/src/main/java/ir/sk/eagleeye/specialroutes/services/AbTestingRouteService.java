package ir.sk.eagleeye.specialroutes.services;

import ir.sk.eagleeye.specialroutes.exception.NoRouteFound;
import ir.sk.eagleeye.specialroutes.model.AbTestingRoute;
import ir.sk.eagleeye.specialroutes.repository.AbTestingRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/14/2020.
 */
@Service
public class AbTestingRouteService {

    @Autowired
    private AbTestingRouteRepository abTestingRouteRepository;

    public AbTestingRoute getRoute(String serviceName) {
        AbTestingRoute route = abTestingRouteRepository.findByServiceName(serviceName);

        if (route == null) {
            throw new NoRouteFound();
        }

        return route;
    }

    public void saveAbTestingRoute(AbTestingRoute route) {

        abTestingRouteRepository.save(route);

    }

    public void updateRouteAbTestingRoute(AbTestingRoute route) {
        abTestingRouteRepository.save(route);
    }

    public void deleteRoute(AbTestingRoute route) {
        abTestingRouteRepository.delete(route.getServiceName());
    }
}
