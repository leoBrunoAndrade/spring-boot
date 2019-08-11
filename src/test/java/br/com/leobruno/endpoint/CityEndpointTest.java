package br.com.leobruno.endpoint;

import br.com.leobruno.model.City;
import br.com.leobruno.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CityEndpointTest {

    @MockBean
    private CityService cityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;


    @Test
    public void trySaveCitySucessShouldReturnStatus201() throws Exception {
        City city = new City();
        city.name = "Recife";
        city.state = "PE";
      //  objectMapper.writeValueAsString(city)

        // "{\"name\":\"Recife\",\"state\":\"PE\"}"

        ResponseEntity<City> cityResponseEntity = new ResponseEntity<>(city, HttpStatus.CREATED);
        Mockito.doReturn(cityResponseEntity).when(cityService).save(Mockito.any());

        mvc.perform(post("/v1/cities").content(objectMapper.writeValueAsString(city))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void trySaveCityWithInvalidFieldShouldReturnStatus400() throws Exception {
        City city = new City();
        city.state = "PE";

        ResponseEntity<City> cityResponseEntity = new ResponseEntity<>(city, HttpStatus.BAD_REQUEST);
        Mockito.doReturn(cityResponseEntity).when(cityService).save(Mockito.any());

        mvc.perform(post("/v1/cities").content(objectMapper.writeValueAsString(city))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryFindByNameCitySucess200() throws Exception {

        ResponseEntity<City> cityResponseEntity = new ResponseEntity<>(new City(), HttpStatus.OK);
        Mockito.doReturn(cityResponseEntity).when(cityService).findByName(Mockito.any());

        mvc.perform(get("/v1/cities/name/{name}","Recife")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void tryFindByStateCitySucess200() throws Exception {

        ResponseEntity<City> cityResponseEntity = new ResponseEntity<>(new City(), HttpStatus.OK);
        Mockito.doReturn(cityResponseEntity).when(cityService).findByName(Mockito.any());

        mvc.perform(get("/v1/cities/name/{state}","PE")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }



}
