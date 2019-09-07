package br.com.leobruno.endpoint;

import br.com.leobruno.model.Customer;
import br.com.leobruno.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(customerServiceInt.save(customer), HttpStatus.CREATED);
    }

    @GetMapping (path = "/name/{name}")
    public  ResponseEntity<?> findByName(@PathVariable("name") String name){
        return new ResponseEntity<>(customerServiceInt.findByName(name),HttpStatus.OK);
    }

    @GetMapping (path = "/{id}")
    public  ResponseEntity<?> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerServiceInt.findById(id),HttpStatus.OK);
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id) {
        customerServiceInt.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping (path = "/{id}")
    public ResponseEntity<?> updateName (@RequestBody String name, @PathVariable("id") Long id){
        return new ResponseEntity<>(customerServiceInt.updateName(name,id),HttpStatus.OK);
    }



}
