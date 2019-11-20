package br.com.leobruno.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.leobruno.dao.DaoCity;
import br.com.leobruno.model.City;
import br.com.leobruno.validate.ValidateCity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CityServiceTest {
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private DaoCity daoCity;
	
	@MockBean
	private ValidateCity validateCity; 
	
	
	
	@Test
	public void tryFindByNameShouldReturnSucess() {
		
		City city = new City();
		city.id = 1L;
		city.name = "s";
		city.state ="a";
		//Given
		given(daoCity.findByName(Mockito.anyString())).willReturn(Arrays.asList(city));
		//When
		List<City> cities = cityService.findByName("s");
		
		assertEquals(cities.size(), 1);
		
	}
	
	@Test
	public void tryFindByStateShouldReturnSucess() {
		
		City city = new City();
		city.id = 1L;
		city.name = "s";
		city.state ="a";
		//Given
		given(daoCity.findByState(Mockito.anyString())).willReturn(Arrays.asList(city));
		//When
		List<City> cities = cityService.findByState("s");
		
		assertEquals(cities.size(), 1);
		
	}
	
	@Test
	public void trySaveReturnSucess() {
		
		City city = new City();
		city.id = 1L;
		city.name = "s";
		city.state ="a";
		//Given
		given(daoCity.save(Mockito.any())).willReturn(city);
		//When
		City c = cityService.save(city);
		
		assertEquals(c.id.intValue(), 1L);
		
	}




}
