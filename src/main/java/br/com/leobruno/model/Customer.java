package br.com.leobruno.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
    private Integer age;

    public Customer(Sex sex, City city, Date born_date, int age){
        this();
        this.sex = sex;
        this.city = city;
        this.born_date = born_date;
        this.age = age;
    }


}
