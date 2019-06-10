package pl.com.b2bnetwork.football.controller;

import org.junit.Assert;
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
import pl.com.b2bnetwork.football.controller.restController.LeagueControllerApi;
import pl.com.b2bnetwork.football.model.LeagueModel;

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class LeagueControllerApiTest {

    @Autowired
    private LeagueControllerApi leagueControllerApi;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllLeagues() {
        Assert.assertNotNull(leagueControllerApi.getAllLeagues());
    }

    @Test
    public void getLeagues() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/leagues/getAllLeagues"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result[0].league_key").value("110"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result[1].league_key").value("512"));
    }

    @Test
    public void exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/leagues/exception"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Błędny URL"));
    }

    @Test
    public void getLeagueByName() throws Exception {
        String leagueName = "Premiership";
        LeagueModel leagueModel = new LeagueModel("123", "Premiership", "123", "123");

        Assert.assertEquals(leagueModel, leagueControllerApi.getLeagueByName(leagueName, leagueModel));
    }
}