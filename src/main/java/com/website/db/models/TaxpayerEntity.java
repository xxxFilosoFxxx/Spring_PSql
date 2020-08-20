package com.website.db.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "taxpayer", schema = "test_bd", catalog = "postgres")
public class TaxpayerEntity {
    private Long id;
    private String name;
    private String surname;
    private String secname;
    private Date date;
    private DuesEntity duesById;
    private IncomeEntity incomesById;
    private Collection<JobEntity> jobsById;

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
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 254)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "secname", nullable = true, length = 254)
    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxpayerEntity that = (TaxpayerEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(secname, that.secname) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, secname, date);
    }

    @OneToOne(mappedBy = "taxpayerByIdTaxpayer")
    public DuesEntity getDuesById() {
        return duesById;
    }

    public void setDuesById(DuesEntity duesById) {
        this.duesById = duesById;
    }

    @OneToOne(mappedBy = "taxpayerByIdTaxpayer")
    public IncomeEntity getIncomesById() {
        return incomesById;
    }

    public void setIncomesById(IncomeEntity incomesById) {
        this.incomesById = incomesById;
    }

    @OneToMany(mappedBy = "taxpayerByIdTaxpayer")
    public Collection<JobEntity> getJobsById() {
        return jobsById;
    }

    public void setJobsById(Collection<JobEntity> jobsById) {
        this.jobsById = jobsById;
    }

    public TaxpayerEntity() {}

    public TaxpayerEntity(String name, String surname, String secname) {
        this.name = name;
        this.surname = surname;
        this.secname = secname;
        java.util.Date utilDate = new java.util.Date();
        this.date = convert(utilDate);

    }

    private Date convert(java.util.Date utilDate) {
        return new Date(utilDate.getTime());
    }
}
