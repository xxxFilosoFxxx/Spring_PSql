package com.website.db.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dues", schema = "test_bd", catalog = "postgres")
public class DuesEntity {
    private Long id;
    private Double incomeTaxes;
    private Long idTaxpayer;
    private Long idInstitutions;
    private TaxpayerEntity taxpayerByIdTaxpayer;
    private InstitutionsEntity institutionsByIdInstitutions;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "id_taxpayer", nullable = true)
    public Long getIdTaxpayer() {
        return idTaxpayer;
    }

    public void setIdTaxpayer(Long idTaxpayer) {
        this.idTaxpayer = idTaxpayer;
    }

    @Basic
    @Column(name = "id_institutions", nullable = true)
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
        DuesEntity that = (DuesEntity) o;
        return id == that.id &&
                Objects.equals(incomeTaxes, that.incomeTaxes) &&
                Objects.equals(idTaxpayer, that.idTaxpayer) &&
                Objects.equals(idInstitutions, that.idInstitutions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, incomeTaxes, idTaxpayer, idInstitutions);
    }

    @OneToOne
    @JoinColumn(name = "id_taxpayer", referencedColumnName = "id")
    public TaxpayerEntity getTaxpayerByIdTaxpayer() {
        return taxpayerByIdTaxpayer;
    }

    public void setTaxpayerByIdTaxpayer(TaxpayerEntity taxpayerByIdTaxpayer) {
        this.taxpayerByIdTaxpayer = taxpayerByIdTaxpayer;
    }

    @OneToOne
    @JoinColumn(name = "id_institutions", referencedColumnName = "id")
    public InstitutionsEntity getInstitutionsByIdInstitutions() {
        return institutionsByIdInstitutions;
    }

    public void setInstitutionsByIdInstitutions(InstitutionsEntity institutionsByIdInstitutions) {
        this.institutionsByIdInstitutions = institutionsByIdInstitutions;
    }
}
