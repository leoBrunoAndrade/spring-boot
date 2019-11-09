package br.com.leobruno.dao;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface DaoGeneric <T> extends JpaRepository <T,Long> {

    List<T> findByName(@Param("name") String name);
    boolean existsByName(@Param("name") String name);
    
    Page<T> findByName(@Param("name") String name,Pageable page);
    
    
    default Example<T> convertClassToExample(T e) {
		return Example.of(e);
  
    }


}
