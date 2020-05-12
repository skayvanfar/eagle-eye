package ir.sk.eagleeye.organization.repository;
import static org.hamcrest.CoreMatchers.is;

import ir.sk.eagleeye.organization.Application;
import ir.sk.eagleeye.organization.model.Organization;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2020.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OrganizationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrganizationRepository repository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findById() {
        // given
        Organization organization = new Organization("1", "OCF");
        entityManager.persist(organization);
        entityManager.flush();

        // when
        Organization found = repository.findById(organization.getId());

        // then
        assertThat(found.getName(), is(organization.getName()));
    }
}