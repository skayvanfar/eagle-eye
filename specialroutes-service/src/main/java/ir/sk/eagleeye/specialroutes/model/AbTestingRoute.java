package ir.sk.eagleeye.specialroutes.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 3/13/2020.
 */
@Data
@RequiredArgsConstructor
@Entity
@Table(name = "abtesting")
public class AbTestingRoute {

    @Id
    @Column(name = "service_name", nullable = false)
    String serviceName;

    @Column(name = "active", nullable = false)
    String active;

    @Column(name = "endpoint", nullable = false)
    String endpoint;

    @Column(name = "weight", nullable = false)
    Integer weight;

}
