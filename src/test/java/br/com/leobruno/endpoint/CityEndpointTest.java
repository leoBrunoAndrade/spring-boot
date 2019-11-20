package br.com.leobruno.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.leobruno.model.City;
import br.com.leobruno.service.CityService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CityEndpointTest {

    @MockBean
    private CityService cityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    private  static List<City> cities;

    @BeforeClass
    public static void generateListCity (){
        City city = new City();
        city.state = "PE";
        city.name = "Recife";
        city.id = 1L;
        cities = new ArrayList<>();
        cities.add(city);
    }


    @Test
    public void trySaveCitySucessShouldReturnStatus201() throws Exception {
        City city = new City();
        city.name = "Recife";
        city.state = "PE";

        Mockito.doReturn(city).when(cityService).save(Mockito.any());

        mvc.perform(post("/v1/cities").content(objectMapper.writeValueAsString(city))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void trySaveCityWithInvalidFieldShouldReturnStatus400() throws Exception {
        City city = new City();
        city.state = "PE";

        Mockito.doReturn(city).when(cityService).save(Mockito.any());

        mvc.perform(post("/v1/cities").content(objectMapper.writeValueAsString(city))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryFindByNameCitySucess200() throws Exception {

        Mockito.doReturn(cities).when(cityService).findByName(Mockito.any());

        mvc.perform(get("/v1/cities/name/{name}","Recife")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void tryFindByStateCitySucess200() throws Exception {


        Mockito.doReturn(cities).when(cityService).findByName(Mockito.any());

        mvc.perform(get("/v1/cities/name/{state}","PE")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
