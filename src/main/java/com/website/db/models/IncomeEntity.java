package com.website.db.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "income", schema = "test_bd", catalog = "postgres")
public class IncomeEntity {
    private Long id;
    private Double amount;
    private Long idTaxpayer;
    private Long idBank;
    private TaxpayerEntity taxpayerByIdTaxpayer;
    private BankEntity bankByIdBank;

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

    @Basic
    @Column(name = "id_taxpayer", nullable = false, insertable = false, updatable = false)
    public Long getIdTaxpayer() {
        return idTaxpayer;
    }

    public void setIdTaxpayer(Long idTaxpayer) {
        this.idTaxpayer = idTaxpayer;
    }

    @Basic
    @Column(name = "id_bank", nullable = true, insertable = false, updatable = false)
    public Long getIdBank() {
        return idBank;
    }

    public void setIdBank(Long idBank) {
        this.idBank = idBank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomeEntity that = (IncomeEntity) o;
        return id == that.id &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(idTaxpayer, that.idTaxpayer) &&
                Objects.equals(idBank, that.idBank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, idTaxpayer, idBank);
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
    @JoinColumn(name = "id_bank", referencedColumnName = "id")
    public BankEntity getBankByIdBank() {
        return bankByIdBank;
    }

    public void setBankByIdBank(BankEntity bankByIdBank) {
        this.bankByIdBank = bankByIdBank;
    }
}
