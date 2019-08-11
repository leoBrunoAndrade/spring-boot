package br.com.leobruno.dao;

import br.com.leobruno.model.EntityGeneric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface DaoGeneric <T extends EntityGeneric> extends JpaRepository <T,Long> {

    T findByName(@Param("name") String name);
    boolean existsByName(@Param("name") String name);


}