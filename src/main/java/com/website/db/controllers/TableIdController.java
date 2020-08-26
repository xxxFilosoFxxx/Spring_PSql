package com.website.db.controllers;

import com.website.db.models.*;
import com.website.db.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Controller
public class TableIdController {

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

    @GetMapping("/table/{id}")
    public String tableInfo(@PathVariable(value = "id") long taxpayerId, Model model) throws SQLException {
        if(!taxpayerRepository.existsById(taxpayerId)) {
            return "redirect:/table";
        }

        Optional<TaxpayerEntity> taxpayer = taxpayerRepository.findById(taxpayerId);
        ArrayList<TaxpayerEntity> res = new ArrayList<>();
        taxpayer.ifPresent(res::add);
        model.addAttribute("taxpayer", res);

        Collection<JobEntity> jobs = jobRepository.findByIdTaxpayer(taxpayerId);
        model.addAttribute("job", jobs);

        Collection<IncomeEntity> income = incomeRepository.findByIdTaxpayer(taxpayerId);
        model.addAttribute("income", income);

        Collection<DuesEntity> dues = duesRepository.findByIdTaxpayer(taxpayerId);
        model.addAttribute("dues", dues);

        Long id_institusion = duesRepository.findByIdInstitutions(taxpayerId);
        Collection<InstitutionsEntity> institutions = institusionsRepository.findByIdDuesAndInstitutions(id_institusion);
        model.addAttribute("institusion", institutions);

        Collection<BankEntity> institusionBanks = bankRepository.findByIdBank(id_institusion);
        model.addAttribute("institusionBanks", institusionBanks);

        return "table-info";
    }
}
