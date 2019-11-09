package br.com.leobruno.validate;

import br.com.leobruno.dao.DaoGeneric;
import br.com.leobruno.erros.EntityConflictException;
import br.com.leobruno.erros.EntityNotFoundExceptionApi;
import br.com.leobruno.handler.InvalidEnumException;
import br.com.leobruno.model.EntityGeneric;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort.Direction;
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
    
    
    public  <E extends Enum<E>> void validEnum(final Class<E> enumClass, String name) {
    	if(!EnumUtils.isValidEnum(enumClass, name) ) {
			throw new InvalidEnumException("Invalid Direction value");
		}
    }

}
