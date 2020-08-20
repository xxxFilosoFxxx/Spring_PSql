package com.website.db.controllers;

import com.website.db.models.DuesEntity;
import com.website.db.models.IncomeEntity;
import com.website.db.models.JobEntity;
import com.website.db.models.TaxpayerEntity;
import com.website.db.repo.DuesRepository;
import com.website.db.repo.IncomeRepository;
import com.website.db.repo.JobRepository;
import com.website.db.repo.TaxpayerRepository;
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

    @GetMapping("/table/{id}")
    public String tableJobInfo(@PathVariable(value = "id") long taxpayerId, Model model) throws SQLException {
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

        return "table-info";
    }


//    @PostMapping("/table/job/{id}/remove")
//    public String tableJobPostRemove(@PathVariable(value = "id") long jobId, Model model) throws SQLException {
//        Job job = jobRepository.findById(jobId).orElseThrow();
//        jobRepository.delete(job);
//        return "redirect:/table";
//    }
}
