package br.com.leobruno.serviceinterface;

import br.com.leobruno.model.EntityGeneric;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface CityServiceInt <T extends EntityGeneric> extends GenericServiceInt<T> {
    ResponseEntity<?> findByState(@PathVariable("state") String state);
}
