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
import pl.com.b2bnetwork.football.controller.restController.TeamControllerApi;

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class TeamControllerApiTest {

    @Autowired
    private TeamControllerApi teamControllerApi;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTeams() {
        Assert.assertNotNull(teamControllerApi.getTeamsBody("2616"));
    }

    @Test
    public void getTeamsBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/team/getTeamBody?id=1111"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result[0].team_name").value("Lokeren"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result[0].players[0].player_key").value("233108643"));

    }
}