package com.website.db.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dues", schema = "test_bd", catalog = "postgres")
public class DuesEntity {
    private long id;
    private Double incomeTaxes;
    private Long idTaxpayer;
    private TaxpayerEntity taxpayerByIdTaxpayer;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "income_taxes", nullable = true, precision = 0)
    public Double getIncomeTaxes() {
        return incomeTaxes;
    }

    public void setIncomeTaxes(Double incomeTaxes) {
        this.incomeTaxes = incomeTaxes;
    }

    @Basic
    @Column(name = "id_taxpayer", nullable = true, insertable = false, updatable = false)
    public Long getIdTaxpayer() {
        return idTaxpayer;
    }

    public void setIdTaxpayer(Long idTaxpayer) {
        this.idTaxpayer = idTaxpayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DuesEntity that = (DuesEntity) o;
        return id == that.id &&
                Objects.equals(incomeTaxes, that.incomeTaxes) &&
                Objects.equals(idTaxpayer, that.idTaxpayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, incomeTaxes, idTaxpayer);
    }

    @ManyToOne
    @JoinColumn(name = "id_taxpayer", referencedColumnName = "id")
    public TaxpayerEntity getTaxpayerByIdTaxpayer() {
        return taxpayerByIdTaxpayer;
    }

    public void setTaxpayerByIdTaxpayer(TaxpayerEntity taxpayerByIdTaxpayer) {
        this.taxpayerByIdTaxpayer = taxpayerByIdTaxpayer;
    }
}
