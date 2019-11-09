package br.com.leobruno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.leobruno.dao.DaoCustomer;
import br.com.leobruno.dto.CustomerDtoGet;
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

	public Page<Customer> findByCustomerPaginable(CustomerDtoGet customerDtoGet, int page, int qtd) {
		Customer customer = customerDtoGet.convertToCustomer();
		
		validateCustomer.validEnum(Direction.class, 
				customerDtoGet.getDirection());
		
		Example<Customer> ex =  daoCustomer.convertClassToExample(customer);
		return daoCustomer.findAll(ex, PageRequest.of(page, qtd,
				Direction.valueOf(customerDtoGet.getDirection()),
				customerDtoGet.getFieldDirection()));
	}

}
