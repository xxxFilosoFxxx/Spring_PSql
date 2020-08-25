package com.website.db.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dues", schema = "test_bd", catalog = "postgres")
public class DuesEntity {
    private Long id;
    private Double incomeTaxes;
    private TaxpayerEntity taxpayerByIdTaxpayer;
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
    @Column(name = "income_taxes", nullable = false, columnDefinition = "0.0")
    public Double getIncomeTaxes() {
        return incomeTaxes;
    }

    public void setIncomeTaxes(Double incomeTaxes) {
        this.incomeTaxes = incomeTaxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DuesEntity that = (DuesEntity) o;
        return id == that.id &&
                Objects.equals(incomeTaxes, that.incomeTaxes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, incomeTaxes);
    }

    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_taxpayer", referencedColumnName = "id")
    public TaxpayerEntity getTaxpayerByIdTaxpayer() {
        return taxpayerByIdTaxpayer;
    }

    public void setTaxpayerByIdTaxpayer(TaxpayerEntity taxpayerByIdTaxpayer) {
        this.taxpayerByIdTaxpayer = taxpayerByIdTaxpayer;
    }

    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_institutions", referencedColumnName = "id")
    public InstitutionsEntity getInstitutionsByIdInstitutions() {
        return institutionsByIdInstitutions;
    }

    public void setInstitutionsByIdInstitutions(InstitutionsEntity institutionsByIdInstitutions) {
        this.institutionsByIdInstitutions = institutionsByIdInstitutions;
    }

    public DuesEntity() {}

    public DuesEntity(Double incomeTaxes) {
        this.incomeTaxes = incomeTaxes;
    }
}
