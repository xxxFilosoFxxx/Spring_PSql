package com.website.db.models;

import com.website.db.repo.TaxpayerRepository;
import org.hibernate.cfg.annotations.reflection.XMLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.website.db.models.Job;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Taxpayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(columnDefinition = "varchar(254) default 'IVANOV'")
    private String surname;

    private String secname;

    private Date date;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="id_job")
//    private Job id_job;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Taxpayer() {}

    public Taxpayer(String name, String surname, String secname) {
        this.name = name;
        this.surname = surname;
        this.secname = secname;
        this.date = null;
    }
}
