package br.com.leobruno.endpoint;

import br.com.leobruno.model.Customer;
import br.com.leobruno.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ("v1/customers")
public class CustomerEndpoint {

    @Autowired
    private CustomerService customerServiceInt;

    @PostMapping
    public ResponseEntity<?> save (@Valid @RequestBody Customer customer){
        return customerServiceInt.save(customer);
    }

    @GetMapping (path = "/name/{name}")
    public  ResponseEntity<?> findByName(@PathVariable("name") String name){
        return customerServiceInt.findByName(name);
    }

    @GetMapping (path = "/{id}")
    public  ResponseEntity<?> findById(@PathVariable("id") Long id){
        return customerServiceInt.findById(id);
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id) {
         return customerServiceInt.delete(id);
    }

    @PatchMapping (path = "/{id}")
    public ResponseEntity<?> updateName (@RequestBody String name, @PathVariable("id") Long id){
       return customerServiceInt.updateName(name,id);
    }



}
