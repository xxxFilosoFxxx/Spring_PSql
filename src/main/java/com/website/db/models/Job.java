package com.website.db.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // TODO:
   // @OneToMany(mappedBy = "id_job")
   // private Set<Taxpayer> id_taxpayer;

    private String name, place;

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Job() {}

    public Job(String name, String place) {
        this.name = name;
        this.place = place;
    }
}
