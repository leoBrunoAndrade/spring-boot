package br.com.leobruno.model;

import br.com.leobruno.util.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Entity
public class Customer extends EntityGeneric {
    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private Sex sex;

    @ManyToOne (cascade = {CascadeType.DETACH})
    @JoinColumn(name = "id_city")
    private City city;

    @Column
    @NotNull
    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date born_date;

    @Column
    @NotNull
    private int age;

    public Customer(Sex sex, City city, Date born_date, int age){
        this();
        this.sex = sex;
        this.city = city;
        this.born_date = born_date;
        this.age = age;
    }

    public Customer (){

    }


    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getBorn_date() {
        return born_date;
    }

    public void setBorn_date(Date born_date) {
        this.born_date = born_date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
