package com.website.db.controllers;

import com.website.db.models.Job;
import com.website.db.models.Taxpayer;
import com.website.db.repo.JobRepository;
import com.website.db.repo.TaxpayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Controller
public class TableController {

    @Autowired
    private TaxpayerRepository taxpayerRepository;

    @GetMapping("/table")
    public String table(Model model) throws SQLException {
        Iterable<Taxpayer> taxplayers = taxpayerRepository.findAll();
        model.addAttribute("taxplayers", taxplayers);
        return "table";
    }

    @GetMapping("/table/add")
    public String tableAdd(Model model) {
        return "table-add";
    }

    @PostMapping("/table/add")
    public String tablePostAdd(@RequestParam String name, @RequestParam String surname, @RequestParam String secname,
                               Model model) throws SQLException {
        Taxpayer taxpayer = new Taxpayer(name, surname, secname);
        Date date = new Date();
        taxpayer.setDate(date);
        taxpayerRepository.save(taxpayer);
        return "redirect:/table";
    }

//    @GetMapping("/table/job/{id}")
//    public String tableJobInfo(@PathVariable(value = "id") long jobId, Model model) throws SQLException {
//        if(!taxpayerRepository.existsById(jobId)) {
//            return "redirect:/table";
//        }
//        Optional<Job> job = jobRepository.findById(jobId);
//        ArrayList<Job> res = new ArrayList<>();
//        job.ifPresent(res::add);
//        model.addAttribute("job", res);
//        return "table-job-info";
//    }
//
//    @GetMapping("/table/job/{id}/edit")
//    public String tableJobEdit(@PathVariable(value = "id") long jobId, Model model) throws SQLException {
//        if(!jobRepository.existsById(jobId)) {
//            return "redirect:/table";
//        }
//
//        Optional<Job> job = jobRepository.findById(jobId);
//        ArrayList<Job> res = new ArrayList<>();
//        job.ifPresent(res::add);
//        model.addAttribute("job", res);
//        return "table-job-edit";
//    }
//
//    @PostMapping("/table/job/{id}/edit")
//    public String tableJobPostEdit(@PathVariable(value = "id") long jobId, @RequestParam String name,
//                                   @RequestParam String place, Model model) throws SQLException {
//        Job job = jobRepository.findById(jobId).orElseThrow();
//        job.setName(name);
//        job.setPlace(place);
//        jobRepository.save(job);
//        return "redirect:/table";
//    }
//
//    @PostMapping("/table/job/{id}/remove")
//    public String tableJobPostRemove(@PathVariable(value = "id") long jobId, Model model) throws SQLException {
//        Job job = jobRepository.findById(jobId).orElseThrow();
//        jobRepository.delete(job);
//        return "redirect:/table";
//    }
}
