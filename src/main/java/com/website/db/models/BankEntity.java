package com.website.db.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "bank", schema = "test_bd", catalog = "postgres")
public class BankEntity {
    private Long id;
    private long cash;
    private Long idJob;
    private Long idInstitutions;
    private JobEntity jobByIdJob;
    private InstitutionsEntity institutionsByIdInstitutions;
    private IncomeEntity incomesById;

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

    @Basic
    @Column(name = "id_job", nullable = false, insertable = false, updatable = false)
    public Long getIdJob() {
        return idJob;
    }

    public void setIdJob(Long idJob) {
        this.idJob = idJob;
    }

    @Basic
    @Column(name = "id_institutions", nullable = true, insertable = false, updatable = false)
    public Long getIdInstitutions() {
        return idInstitutions;
    }

    public void setIdInstitutions(Long idInstitutions) {
        this.idInstitutions = idInstitutions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankEntity that = (BankEntity) o;
        return id == that.id &&
                cash == that.cash &&
                Objects.equals(idJob, that.idJob) &&
                Objects.equals(idInstitutions, that.idInstitutions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cash, idJob, idInstitutions);
    }

    @OneToOne
    @JoinColumn(name = "id_job", referencedColumnName = "id")
    public JobEntity getJobByIdJob() {
        return jobByIdJob;
    }

    public void setJobByIdJob(JobEntity jobByIdJob) {
        this.jobByIdJob = jobByIdJob;
    }

    @ManyToOne
    @JoinColumn(name = "id_institutions", referencedColumnName = "id")
    public InstitutionsEntity getInstitutionsByIdInstitutions() {
        return institutionsByIdInstitutions;
    }

    public void setInstitutionsByIdInstitutions(InstitutionsEntity institutionsByIdInstitutions) {
        this.institutionsByIdInstitutions = institutionsByIdInstitutions;
    }

    @OneToOne(mappedBy = "bankByIdBank")
    public IncomeEntity getIncomesById() {
        return incomesById;
    }

    public void setIncomesById(IncomeEntity incomesById) {
        this.incomesById = incomesById;
    }
}
