package br.com.leobruno.service;

import br.com.leobruno.dao.DaoCustomer;
import br.com.leobruno.model.Customer;
import br.com.leobruno.serviceinterface.CustomerServiceInt;
import br.com.leobruno.validate.ValidateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceInt <Customer> {

    @Autowired
    private DaoCustomer daoCustomer;

    @Autowired
    private ValidateCustomer validateCustomer;

    @Override
    public ResponseEntity<?> save(Customer customer) {
        return new ResponseEntity<>(daoCustomer.save(customer), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> findByName(String name) {
        return new ResponseEntity<>(daoCustomer.findByNameWithLike(name),HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> findById(Long id) {
        validateCustomer.verifyExistsEntity(id,daoCustomer,"Customer");
        return new ResponseEntity<>(daoCustomer.findById(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        validateCustomer.verifyExistsEntity(id, daoCustomer, "Customer");
        daoCustomer.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateName(String name, Long id) {
        validateCustomer.verifyExistsEntity(id, daoCustomer, "Customer");
        return new ResponseEntity<>(daoCustomer.updateByName(name,id),HttpStatus.OK);
    }

}
