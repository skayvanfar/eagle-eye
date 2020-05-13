package ir.sk.eagleeye.zuulsvr.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/13/2020.
 */
@Data
@RequiredArgsConstructor
public class AbTestingRoute {
    String serviceName;
    String active;
    String endpoint;
    Integer weight;
}
