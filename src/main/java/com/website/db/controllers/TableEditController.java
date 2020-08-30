package com.website.db.controllers;

import com.website.db.models.*;
import com.website.db.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class TableEditController {
    @Autowired
    private TaxpayerRepository taxpayerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private DuesRepository duesRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private InstitusionsRepository institusionsRepository;

//    @GetMapping("/table/{id}/edit/income")
//    public String tableIncomeEdit(@PathVariable(value = "id") long taxpayerId, Model model) throws SQLException {
//        Collection<IncomeEntity> income = incomeRepository.findByIdTaxpayer(taxpayerId);
//        model.addAttribute("income", income);
//        return "table-edit-income";
//    }

    @PostMapping("/table/{id}/edit/income")
    public String tablePostIncomeEdit(@PathVariable(value = "id") long taxpayerId, @RequestParam Double amount,
                                      Model model) throws SQLException  {
        Collection<IncomeEntity> income_col = incomeRepository.findByIdTaxpayer(taxpayerId);
        IncomeEntity income = income_col.iterator().next();
        if (income.getId() == null) {
            throw new NoSuchElementException("No value present");
        }
        income.setAmount(amount);
        incomeRepository.save(income);
        return "redirect:/table/{id}";
    }

//    @GetMapping("/table/{id}/edit/dues")
//    public String tableDuesEdit(@PathVariable(value = "id") long taxpayerId, Model model) throws SQLException {
//        Collection<DuesEntity> dues = duesRepository.findByIdTaxpayer(taxpayerId);
//        model.addAttribute("dues", dues);
//        return "table-edit-dues";
//    }

    @PostMapping("/table/{id}/edit/dues")
    public String tablePostDuesEdit(@PathVariable(value = "id") long taxpayerId, @RequestParam Double income_taxes,
                                    Model model) throws SQLException  {
        Collection<DuesEntity> dues_col = duesRepository.findByIdTaxpayer(taxpayerId);
        DuesEntity dues = dues_col.iterator().next();
        if (dues.getId() == null) {
            throw new NoSuchElementException("No value present");
        }
        dues.setIncomeTaxes(income_taxes);
        duesRepository.save(dues);
        return "redirect:/table/{id}";
    }

//    @GetMapping("/table/{id}/edit/institusion")
//    public String tableInstitusionEdit(@PathVariable(value = "id") long taxpayerId, Model model) throws SQLException {
//        Long id_institusion = duesRepository.findByIdInstitutions(taxpayerId);
//        Collection<InstitutionsEntity> institutions = institusionsRepository.findByIdDuesAndInstitutions(id_institusion);
//        model.addAttribute("institusion", institutions);
//        return "table-edit-institution";
//    }

    @PostMapping("/table/{id}/edit/institusion")
    public String tablePostInstitusionEdit(@PathVariable(value = "id") long taxpayerId, @RequestParam String name,
                                           Model model) throws SQLException  {
        Long id_institusion = duesRepository.findByIdInstitutions(taxpayerId);
        Collection<InstitutionsEntity> institutions_col = institusionsRepository.findByIdDuesAndInstitutions(id_institusion);
        InstitutionsEntity institutions = institutions_col.iterator().next();
        if (institutions.getId() == null) {
            throw new NoSuchElementException("No value present");
        }
        institutions.setName(name);
        institusionsRepository.save(institutions);
        return "redirect:/table/{id}";
    }
}
