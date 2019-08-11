package br.com.leobruno.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class City extends EntityGeneric {

     @Column
     @NotNull
     public String state;



}
