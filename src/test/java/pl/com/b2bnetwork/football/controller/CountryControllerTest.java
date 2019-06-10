package pl.com.b2bnetwork.football.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.com.b2bnetwork.TestContext;

import static org.junit.Assert.*;



@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class CountryControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private CountryController countryController;


    @Test
    public void findAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/countries/all"))
                .andExpect(MockMvcResultMatchers.status().is(401));

    }

    @Test
    public void findOneByName() {

    }
}