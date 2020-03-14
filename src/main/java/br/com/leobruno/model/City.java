package br.com.leobruno.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;


@Entity
public class City extends EntityGeneric {

     private static final long serialVersionUID = -2509509471450636146L;
     
     @Column
     @NotBlank
     public String state;



}
