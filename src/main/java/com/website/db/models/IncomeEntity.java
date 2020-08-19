package com.website.db.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "income", schema = "test_bd", catalog = "postgres")
public class IncomeEntity {
    private long id;
    private Double amount;
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
    @Column(name = "amount", nullable = true, precision = 0)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
        IncomeEntity that = (IncomeEntity) o;
        return id == that.id &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(idTaxpayer, that.idTaxpayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, idTaxpayer);
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
