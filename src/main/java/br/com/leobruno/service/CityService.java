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

@Service
public class CityService implements CityServiceInt<City> {

    @Autowired
    private DaoCity daoCity;

    @Autowired
    private ValidateCity validateCity;

    @Override
    @Transactional
    public ResponseEntity<?> save(City city) {
        validateCity.verifyConflictEntityExample(city,daoCity,"City");
        return new ResponseEntity<>(daoCity.save(city), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> findByName(String name) {
        validateCity.verifyExistsEntityByName(name,daoCity,"City");
        return new ResponseEntity<>(daoCity.findByName(name),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByState(String state) {
        validateCity.verifyExistsEntityByState(state,daoCity,"City");
        return new ResponseEntity<>(daoCity.findByState(state),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }


}
