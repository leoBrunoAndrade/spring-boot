package br.com.leobruno.service;

import br.com.leobruno.dao.DaoCity;
import br.com.leobruno.model.City;
import br.com.leobruno.serviceinterface.CityServiceInt;
import br.com.leobruno.validate.ValidateCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CityService implements CityServiceInt<City> {

    private DaoCity daoCity;

    private ValidateCity validateCity;
    
    @Autowired
    public CityService(DaoCity daoCity, ValidateCity validateCity) {
		this.daoCity = daoCity;
		this.validateCity = validateCity;
	}

    @Override
    @Transactional
    public City save(City city) {
        validateCity.verifyConflictEntityExample(city,daoCity,"City");
        return daoCity.save(city);
    }

    @Override
    public List<City> findByName(String name) {
        validateCity.verifyExistsEntityByName(name,daoCity,"City");
        return daoCity.findByName(name);
    }

    @Override
    public List<City> findByState(String state) {
        validateCity.verifyExistsEntityByState(state,daoCity,"City");
        List<City> listCities = daoCity.findByState(state);
        return listCities;
    }


    @Override
    public City findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }



}
