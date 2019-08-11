package br.com.leobruno.serviceinterface;

import br.com.leobruno.model.EntityGeneric;
import org.springframework.http.ResponseEntity;


public interface GenericServiceInt <T extends EntityGeneric> {

    ResponseEntity<?> save (T t);

    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> delete (Long id);

    ResponseEntity<?> findByName(String name);
}
