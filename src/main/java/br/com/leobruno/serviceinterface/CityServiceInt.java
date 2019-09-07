package br.com.leobruno.serviceinterface;

import br.com.leobruno.model.EntityGeneric;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CityServiceInt <T> extends GenericServiceInt<T> {
    List<T> findByState(@PathVariable("state") String state);
}
