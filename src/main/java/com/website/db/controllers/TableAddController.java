package com.website.db.controllers;

import com.website.db.models.*;
import com.website.db.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class TableAddController {
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

    @GetMapping("/table/add")
    public String tableAdd(Model model) {
        return "table-add";
    }

    @PostMapping("/table/add")
    public String tablePostAdd(@RequestParam String name1, @RequestParam String surname, @RequestParam String secname,
                               @RequestParam String name2, @RequestParam String place,
                               @RequestParam double amount,
                               @RequestParam double income_taxes,
                               @RequestParam String name3,
                               @RequestParam long cash,
                               Model model) throws SQLException {
        TaxpayerEntity taxpayer = new TaxpayerEntity(name1, surname, secname);

        JobEntity job = new JobEntity(name2, place);

        IncomeEntity income = new IncomeEntity(amount);

        DuesEntity dues = new DuesEntity(income_taxes);

        InstitutionsEntity institutions = new InstitutionsEntity(name3);

        BankEntity bank = new BankEntity(cash);

        taxpayer.addJob(job);
        taxpayer.addIncome(income);
        taxpayer.addDues(dues);
        job.addBank(bank);
        institutions.addDues(dues);
        institutions.addBank(bank);

        taxpayerRepository.save(taxpayer);

        return "redirect:/table";
    }
}
