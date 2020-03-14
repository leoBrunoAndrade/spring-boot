package br.com.leobruno.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.leobruno.model.Customer;

@Repository
public interface DaoCustomer extends DaoGeneric<Customer>{

    @Modifying
    @Query("update Customer cus set cus.name = :name where cus.id = :id")
    int updateByName(@Param("name") String name, @Param("id") Long id);

    @Query("select cus from Customer cus where name like %:name%")
    List<Customer> findByNameWithLike(@Param("name") String name);

}
