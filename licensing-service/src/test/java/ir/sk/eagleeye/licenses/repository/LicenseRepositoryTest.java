package ir.sk.eagleeye.licenses.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2020.
 */
@RunWith(SpringRunner.class)
@DataJpaTest // Spring boot mock
public class LicenseRepositoryTest {

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