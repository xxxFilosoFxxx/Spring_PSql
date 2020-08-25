package com.website.db.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "bank", schema = "test_bd", catalog = "postgres")
public class BankEntity {
    private Long id;
    private long cash;
    private JobEntity jobByIdJob;
    private InstitutionsEntity institutionsByIdInstitutions;

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
    @Column(name = "cash", nullable = false)
    public long getCash() {
        return cash;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankEntity that = (BankEntity) o;
        return id == that.id &&
                cash == that.cash;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cash);
    }

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_job", referencedColumnName = "id")
    public JobEntity getJobByIdJob() {
        return jobByIdJob;
    }

    public void setJobByIdJob(JobEntity jobByIdJob) {
        this.jobByIdJob = jobByIdJob;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_institutions", referencedColumnName = "id")
    public InstitutionsEntity getInstitutionsByIdInstitutions() {
        return institutionsByIdInstitutions;
    }

    public void setInstitutionsByIdInstitutions(InstitutionsEntity institutionsByIdInstitutions) {
        this.institutionsByIdInstitutions = institutionsByIdInstitutions;
    }

    public BankEntity() {}

    public BankEntity(long cash) {
        this.cash = cash;
    }
}
