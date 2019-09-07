package br.com.leobruno.service;

import br.com.leobruno.dao.DaoCustomer;
import br.com.leobruno.model.Customer;
import br.com.leobruno.serviceinterface.CustomerServiceInt;
import br.com.leobruno.validate.ValidateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInt <Customer> {

    @Autowired
    private DaoCustomer daoCustomer;

    @Autowired
    private ValidateCustomer validateCustomer;

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return daoCustomer.save(customer);
    }

    @Override
    public List<Customer> findByName(String name) {
        return daoCustomer.findByName(name);
    }


    @Override
    public Customer findById(Long id) {
        validateCustomer.verifyExistsEntity(id,daoCustomer,"Customer");
        return daoCustomer.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validateCustomer.verifyExistsEntity(id, daoCustomer, "Customer");
        daoCustomer.deleteById(id);

    }

    @Override
    @Transactional
    public Object updateName( String name, Long id) {
        validateCustomer.verifyExistsEntity(id, daoCustomer, "Customer");
        return  daoCustomer.updateByName(name,id);
    }

}
