package br.com.leobruno.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;


@Entity
public class City extends EntityGeneric {


	 @Column
     @NotBlank
     public String state;



}
