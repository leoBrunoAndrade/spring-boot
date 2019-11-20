package br.com.leobruno.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.leobruno.model.Customer;
import br.com.leobruno.model.dto.CustomerDtoGet;
import br.com.leobruno.service.CustomerService;

@RestController
@RequestMapping ("v1/customers")
public class CustomerController {

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
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PatchMapping (path = "/{id}")
    public ResponseEntity<?> updateName (@RequestBody String name, @PathVariable("id") Long id){
        return new ResponseEntity<>(customerServiceInt.updateName(name,id),HttpStatus.OK);
    }
    
    @GetMapping(path = "/findByPaginable")
    public ResponseEntity<?> findByCustomerPaginable(@RequestParam(required = false) String name, @RequestParam(defaultValue = "id") String fieldDirection, 
    		@RequestParam(defaultValue = "ASC")  String direction, @RequestParam int page, @RequestParam int qtd){
    	Page<Customer> pageCustomer = customerServiceInt.findByCustomerPaginable(name, page, qtd, direction, fieldDirection);
		return new ResponseEntity<>(pageCustomer,HttpStatus.OK);
    	
    }
    

}
