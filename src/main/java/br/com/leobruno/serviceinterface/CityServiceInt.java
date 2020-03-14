package br.com.leobruno.serviceinterface;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

public interface CityServiceInt <T> extends GenericServiceInt<T> {
    List<T> findByState(@PathVariable("state") String state);
}
