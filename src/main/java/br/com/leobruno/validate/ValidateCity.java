package br.com.leobruno.validate;

import br.com.leobruno.dao.DaoCity;
import br.com.leobruno.erros.EntityNotFoundExceptionApi;
import org.springframework.stereotype.Component;

@Component
public class ValidateCity extends ValidateGeneric {

    public void verifyExistsEntityByState(String state, DaoCity daoCity, String nameEntity){
        if(!daoCity.existsByState(state))
            throw new EntityNotFoundExceptionApi(nameEntity+" not found by state : " + state);
    }

}
