package com.website.db.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "institutions", schema = "test_bd", catalog = "postgres")
public class InstitutionsEntity {
    private Long id;
    private String name;
    private Collection<BankEntity> banksById = new ArrayList<>();
    private DuesEntity duesById;

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
    @Column(name = "name", nullable = true, length = 254)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstitutionsEntity that = (InstitutionsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "institutionsByIdInstitutions")
    public Collection<BankEntity> getBanksById() {
        return banksById;
    }

    public void setBanksById(Collection<BankEntity> banksById) {
        this.banksById = banksById;
    }

    public void addBank(BankEntity bankEntity) {
        bankEntity.setInstitutionsByIdInstitutions(this);
        this.banksById.add(bankEntity);
    }

    @OneToOne(mappedBy = "institutionsByIdInstitutions")
    public DuesEntity getDuesById() {
        return duesById;
    }

    public void setDuesById(DuesEntity duesById) {
        this.duesById = duesById;
    }

    public void addDues(DuesEntity duesEntity) {
        duesEntity.setInstitutionsByIdInstitutions(this);
        this.duesById = duesEntity;
    }

    public InstitutionsEntity() {}

    public InstitutionsEntity(String name) {
        this.name = name;
    }
}
