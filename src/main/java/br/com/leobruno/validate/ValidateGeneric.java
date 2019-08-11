package br.com.leobruno.validate;

import br.com.leobruno.dao.DaoGeneric;
import br.com.leobruno.erros.EntityConflictException;
import br.com.leobruno.erros.EntityNotFoundExceptionApi;
import br.com.leobruno.model.EntityGeneric;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class ValidateGeneric {

    //Remove | update | FindById
    public void verifyExistsEntity(Long id, DaoGeneric daoGeneric,String nameEntity){
        if(!daoGeneric.existsById(id))
            throw new EntityNotFoundExceptionApi(nameEntity +" not found by id: " + id);
    }

    public void verifyExistsEntityByName(String name, DaoGeneric daoGeneric,String nameEntity){
        if(!daoGeneric.existsByName(name))
            throw new EntityNotFoundExceptionApi(nameEntity +" not found by name : " + name);
    }

    // update |Save
    public void verifyConflictEntityExample(EntityGeneric entityGeneric, DaoGeneric daoGeneric,String nameEntity){
        Example example = Example.of(entityGeneric);
        if(!daoGeneric.findAll(example).isEmpty())
            throw new EntityConflictException(nameEntity + " Entity conflict by name " + entityGeneric.name);
    }

}