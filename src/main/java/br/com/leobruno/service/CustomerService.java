package br.com.leobruno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leobruno.dao.DaoCustomer;
import br.com.leobruno.model.Customer;
import br.com.leobruno.serviceinterface.CustomerServiceInt;
import br.com.leobruno.validate.ValidateCustomer;

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

	public Page<Customer> findByCustomerPaginable(String name, int page, int qtd, String direction, String fieldDirection) {
		if(direction!=null) {
			direction = direction.toUpperCase();
		}
		validateCustomer.validEnum(Direction.class, 
				direction.toUpperCase());
		
		Customer customer = new Customer();
		customer.name = name;
		Example<Customer> ex =  daoCustomer.convertClassToExample(customer);
		
		PageRequest pr = PageRequest.of(page, qtd,
				Direction.valueOf(direction), fieldDirection);
		
		return daoCustomer.findAll(ex,pr);
	}
	
	public Page<Customer> findByCustomerPaginable(Pageable pageable) {
		
		return daoCustomer.findAll(pageable);
	}

}
