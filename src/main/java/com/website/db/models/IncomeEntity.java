package com.website.db.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "income", schema = "test_bd", catalog = "postgres")
public class IncomeEntity {
    private Long id;
    private Double amount;
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
    @Column(name = "amount", nullable = false, columnDefinition = "0.0")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomeEntity that = (IncomeEntity) o;
        return id == that.id &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @OneToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name = "id_taxpayer", referencedColumnName = "id")
    public TaxpayerEntity getTaxpayerByIdTaxpayer() {
        return taxpayerByIdTaxpayer;
    }

    public void setTaxpayerByIdTaxpayer(TaxpayerEntity taxpayerByIdTaxpayer) {
        this.taxpayerByIdTaxpayer = taxpayerByIdTaxpayer;
    }

    public IncomeEntity() {}

    public IncomeEntity(Double amount) {
        this.amount = amount;
    }
}
