package br.com.leobruno.endpoint;

import br.com.leobruno.model.City;
import br.com.leobruno.model.Customer;
import br.com.leobruno.service.CustomerService;
import br.com.leobruno.util.Sex;
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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerEndpointTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;


    @Test
    public void tryEndpointSaveCustomerSucessShouldReturn201() throws Exception {
        Customer customer = new Customer(Sex.M,new City(),new Date(),27);
        customer.name = "Cliente 1";

        ResponseEntity<Customer> customerResponseEntity = new ResponseEntity<>(customer, HttpStatus.CREATED);
        Mockito.doReturn(customerResponseEntity).when(customerService).save(Mockito.any());

        mvc.perform(post("/v1/customers")
                .content(objectMapper.writeValueAsString(customer))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void tryEndpointSaveCustomerWithInvalidFieldShouldReturn400() throws Exception {
        Customer customer = new Customer(Sex.M,new City(),new Date(),27);

        ResponseEntity<Customer> customerResponseEntity = new ResponseEntity<>(customer, HttpStatus.BAD_REQUEST);
        Mockito.doReturn(customerResponseEntity).when(customerService).save(Mockito.any());

        mvc.perform(post("/v1/customers")
                .content(objectMapper.writeValueAsString(customer))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void tryEndpointFindByIdSucess200() throws Exception {
        Customer customer = new Customer(Sex.M,new City(),new Date(),27);
        customer.name = "Cliente 1";

        ResponseEntity<Customer> customerResponseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
        Mockito.doReturn(customerResponseEntity).when(customerService).save(Mockito.any());

        mvc.perform(get("/v1/customers/{id}","1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void tryEndpointDeleteCustomerSucessShouldReturn200() throws Exception {
        ResponseEntity<Customer> customerResponseEntity = new ResponseEntity<>( HttpStatus.OK);
        Mockito.doReturn(customerResponseEntity).when(customerService).delete(Mockito.anyLong());

        mvc.perform(delete("/v1/customers/{id}","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .isOk());
    }

    @Test
    public void tryEndpointUdateNameCustomerSucess200() throws Exception{
        Customer customer = new Customer(Sex.M,new City(),new Date(),27);
        customer.name = "Cliente 1";

        ResponseEntity<Customer> customerResponseEntity = new ResponseEntity<>(customer,HttpStatus.OK);

        mvc.perform(patch("/v1/customers/{id}","1")
                .param("name","Cliente 2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
