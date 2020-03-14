package br.com.leobruno.validate;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import br.com.leobruno.dao.DaoGeneric;
import br.com.leobruno.erros.EntityConflictException;
import br.com.leobruno.erros.EntityNotFoundExceptionApi;
import br.com.leobruno.model.EntityGeneric;

@Component
public class ValidateGeneric {

    //Remove | update | FindById
    public void verifyExistsEntity(final Long id, final DaoGeneric<?> daoGeneric,final String nameEntity){
        if(!daoGeneric.existsById(id))
            throw new EntityNotFoundExceptionApi(nameEntity +" not found by id: " + id);
    }

    public void verifyExistsEntityByName(final String name, final DaoGeneric<?> daoGeneric,final String nameEntity){
        if(!daoGeneric.existsByName(name))
            throw new EntityNotFoundExceptionApi(nameEntity +" not found by name : " + name);
    }

    // update |Save
    public void verifyConflictEntityExample(final EntityGeneric entityGeneric, final DaoGeneric<?> daoGeneric,final String nameEntity){
        final Example example = Example.of(entityGeneric);
        if(!daoGeneric.findAll(example).isEmpty())
            throw new EntityConflictException(nameEntity + " Entity conflict by name " + entityGeneric.name);
    }
    
    
    public  <E extends Enum<E>> void validEnum(final Class<E> enumClass, final String name) {
    	if(!EnumUtils.isValidEnum(enumClass, name) ) {
			throw new InvalidEnumException("Invalid Direction value");
		}
    }

}
