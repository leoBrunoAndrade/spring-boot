package br.com.leobruno.serviceinterface;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerServiceInt<T> extends GenericServiceInt <T> {

    Object updateName (@RequestParam String name, @PathVariable Long id);
    

}
