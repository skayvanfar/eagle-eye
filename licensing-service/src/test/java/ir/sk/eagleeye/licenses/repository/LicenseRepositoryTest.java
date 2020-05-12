package ir.sk.eagleeye.licenses.repository;

import ir.sk.eagleeye.licenses.model.Organization;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2020.
 */
@RunWith(SpringRunner.class)
@DataJpaTest // Spring boot mock
/*@DataJpaTest provides some standard setup needed for testing the persistence layer:

configuring H2, an in-memory database
setting Hibernate, Spring Data, and the DataSource
performing an @EntityScan
turning on SQL logging*/
public class LicenseRepositoryTest {

    /**
     * To carry out some DB operation, we need some records already setup in our database.
     * To setup this data, we can use TestEntityManager. The TestEntityManager provided by Spring Boot
     * is an alternative to the standard JPA EntityManager that provides methods commonly used when writing tests
     */
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LicenseRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findByOrganizationId() {
    }

    @Test
    public void findByOrganizationIdAndLicenseId() {
    }
}