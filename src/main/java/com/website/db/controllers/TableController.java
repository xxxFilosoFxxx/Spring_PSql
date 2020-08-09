package com.website.db.controllers;

import com.website.db.models.Job;
import com.website.db.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TableController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/table")
    public String table(Model model) {
        Iterable<Job> jobs = jobRepository.findAll();
        model.addAttribute("jobs", jobs);
        return "table";
    }

    @GetMapping("/table/add")
    public String tableAdd(Model model) {
        return "table-add";
    }

    @PostMapping("/table/add")
    public String tablePostAdd(@RequestParam String name, @RequestParam String place,
                               Model model) {
        Job job = new Job(name, place);
        jobRepository.save(job);
        return "redirect:/table";
    }

    @GetMapping("/table/{id}")
    public String tableInfo(@PathVariable(value = "id") long jobId, Model model) {
        if(!jobRepository.existsById(jobId)) {
            return "redirect:/table";
        }
        Optional<Job> job = jobRepository.findById(jobId);
        ArrayList<Job> res = new ArrayList<>();
        job.ifPresent(res::add);
        model.addAttribute("job", res);
        return "table-info";
    }

    @GetMapping("/table/{id}/edit")
    public String tableEdit(@PathVariable(value = "id") long jobId, Model model) {
        if(!jobRepository.existsById(jobId)) {
            return "redirect:/table";
        }

        Optional<Job> job = jobRepository.findById(jobId);
        ArrayList<Job> res = new ArrayList<>();
        job.ifPresent(res::add);
        model.addAttribute("job", res);
        return "table-edit";
    }

    @PostMapping("/table/{id}/edit")
    public String tablePostEdit(@PathVariable(value = "id") long jobId, @RequestParam String name,
                                @RequestParam String place, Model model) {
        Job job = jobRepository.findById(jobId).orElseThrow();
        job.setName(name);
        job.setPlace(place);
        jobRepository.save(job);
        return "redirect:/table";
    }

    @PostMapping("/table/{id}/remove")
    public String tablePostRemove(@PathVariable(value = "id") long jobId, Model model) {
        Job job = jobRepository.findById(jobId).orElseThrow();
        jobRepository.delete(job);
        return "redirect:/table";
    }

}
