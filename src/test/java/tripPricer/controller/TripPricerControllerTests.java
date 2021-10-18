package tripPricer.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tripPricer.Provider;
import tripPricer.service.TripPricerService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TripPricerControllerTests {

    @MockBean
    private TripPricerService tripPricerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPriceShouldReturn200AndProviderList() throws Exception {
        Provider provider = new Provider(UUID.fromString("00000000-0000-0001-0000-000000000002"),"providerName",10);
        List<Provider> expectedList = Collections.singletonList(provider);

        when(tripPricerService.getPrice(anyString(), any(UUID.class), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(expectedList);

        String expectedResponseJson = "[{\"name\":\"providerName\",\"price\":10.0,\"tripId\":\"00000000-0000-0001-0000-000000000002\"}]";

        mockMvc.perform(get("/getPrice")
                .param("apiKey", "apiKey")
                .param("attractionId", String.valueOf(UUID.randomUUID()))
                .param("adults", String.valueOf(2))
                .param("children", String.valueOf(1))
                .param("nightsStay", String.valueOf(1))
                .param("rewardsPoints", String.valueOf(10)))
                .andExpect(mvcResult -> {
                    Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
                    Assert.assertTrue(mvcResult.getResponse().getContentAsString().equalsIgnoreCase(expectedResponseJson));
                });
    }

    @Test
    public void getProviderNameShouldReturn200AndProviderName() throws Exception {
        when(tripPricerService.getProviderName("apiKey", 2)).thenReturn("providerName");

        mockMvc.perform(get("/getProviderName")
        .param("apiKey", "apiKey")
        .param("adults", String.valueOf(2)))
                .andExpect(mvcResult -> {
                    Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
                    Assert.assertTrue(mvcResult.getResponse().getContentAsString().equalsIgnoreCase("providerName"));
                });
    }

}
