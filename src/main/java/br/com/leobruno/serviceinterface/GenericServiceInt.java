package br.com.leobruno.serviceinterface;

import br.com.leobruno.model.EntityGeneric;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface GenericServiceInt <T> {

    T save (T t);

    T findById(Long id);

    void delete (Long id);

    List<T> findByName(String name);
}
