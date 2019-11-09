package br.com.leobruno.serviceinterface;

import java.util.List;


public interface GenericServiceInt <T> {

    T save (T t);

    T findById(Long id);

    void delete (Long id);

    List<T> findByName(String name);
}
