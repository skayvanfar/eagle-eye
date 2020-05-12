package ir.sk.eagleeye.organization.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2020.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(OrganizationServiceController.class)
public class OrganizationServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getOrganization() {
        /*        mockMvc.perform(get("v1/organizations/{organizationId}/licenses"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Welcome to...")));*/
    }

    @Test
    public void updateOrganization() {
    }

    @Test
    public void saveOrganization() {
    }

    @Test
    public void deleteOrganization() {
    }
}