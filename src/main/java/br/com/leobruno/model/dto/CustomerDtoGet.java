package br.com.leobruno.model.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.leobruno.model.Customer;
import br.com.leobruno.model.Sex;
import lombok.Data;
@Data
public class CustomerDtoGet {
	
	private String name;
	private Sex sex;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date born_date;
	private Integer age;
	
	private String direction;
	private String fieldDirection;
	
	
	
	public Customer convertToCustomer() {
		ModelMapper mp = new ModelMapper();
		Customer customer = mp.map(this, Customer.class);
		return customer;
	}

}
