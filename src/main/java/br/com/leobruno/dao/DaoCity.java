package br.com.leobruno.dao;

import br.com.leobruno.model.City;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  interface  DaoCity extends DaoGeneric<City> {
    boolean existsByState(@Param("state") String state);
    List<City> findByState(@Param("state") String state);

}
