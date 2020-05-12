package ir.sk.eagleeye.licenses.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author <a href="kayvanfar.sj@gmail.com">Saeed Kayvanfar</a> on 5/12/2020.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LicenseServiceController.class) // Spring boot mock
public class LicenseServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getLicenses() throws Exception {
/*        mockMvc.perform(get("v1/organizations/{organizationId}/licenses"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Welcome to...")));*/
    }

    @Test
    public void testGetLicenses() {
    }

    @Test
    public void getLicensesWithClient() {
    }

    @Test
    public void updateLicenses() {
    }

    @Test
    public void saveLicenses() {
    }

    @Test
    public void deleteLicenses() {
    }
}