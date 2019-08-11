package br.com.leobruno.serviceinterface;

import br.com.leobruno.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerServiceInt<T extends Customer> extends GenericServiceInt <T> {

    ResponseEntity<?> updateName (@RequestParam String name, @PathVariable Long id);

}
