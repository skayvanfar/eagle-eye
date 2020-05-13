package ir.sk.eagleeye.specialroutes.controllers;

import ir.sk.eagleeye.specialroutes.model.AbTestingRoute;
import ir.sk.eagleeye.specialroutes.services.AbTestingRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/14/2020.
 */
@RestController
@RequestMapping(value = "v1/route/")
public class SpecialRoutesServiceController {

    @Autowired
    AbTestingRouteService routeService;

    @GetMapping(value = "abtesting/{serviceName}")
    public AbTestingRoute abstestings(@PathVariable("serviceName") String serviceName) {
        return routeService.getRoute(serviceName);
    }
}
