package com.website.db.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "job", schema = "test_bd", catalog = "postgres")
public class JobEntity {
    private Long id;
    private String name;
    private String place;
    private BankEntity banksById;
    private TaxpayerEntity taxpayerByIdTaxpayer;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 254)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "place", nullable = true, length = 254)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobEntity jobEntity = (JobEntity) o;
        return id == jobEntity.id &&
                Objects.equals(name, jobEntity.name) &&
                Objects.equals(place, jobEntity.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, place);
    }

    @OneToOne(mappedBy = "jobByIdJob", orphanRemoval = true, cascade = CascadeType.ALL)
    public BankEntity getBanksById() {
        return banksById;
    }

    public void setBanksById(BankEntity banksById) {
        this.banksById = banksById;
    }

    public void addBank(BankEntity bankEntity) {
        bankEntity.setJobByIdJob(this);
        this.banksById = bankEntity;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_taxpayer", referencedColumnName = "id")
    public TaxpayerEntity getTaxpayerByIdTaxpayer() {
        return taxpayerByIdTaxpayer;
    }

    public void setTaxpayerByIdTaxpayer(TaxpayerEntity taxpayerByIdTaxpayer) {
        this.taxpayerByIdTaxpayer = taxpayerByIdTaxpayer;
    }

    public JobEntity() {}

    public JobEntity(String name, String place) {
        this.name = name;
        this.place = place;
    }
}
