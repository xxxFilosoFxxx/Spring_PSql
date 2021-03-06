package com.website.db.controllers;

import com.website.db.models.TaxpayerEntity;
import com.website.db.repo.TaxpayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TableController {

    @Autowired
    private TaxpayerRepository taxpayerRepository;

    @GetMapping("/table")
    public String table(Model model) throws SQLException {
        Iterable<TaxpayerEntity> taxplayers = taxpayerRepository.findAll();
        model.addAttribute("taxplayers", taxplayers);
        return "table";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/table/{id}/edit")
    public String tableEdit(@PathVariable(value = "id") long taxpayerId, Model model) throws SQLException {
        if(!taxpayerRepository.existsById(taxpayerId)) {
            return "redirect:/table";
        }

        Optional<TaxpayerEntity> taxpayer = taxpayerRepository.findById(taxpayerId);
        ArrayList<TaxpayerEntity> res = new ArrayList<>();
        taxpayer.ifPresent(res::add);
        model.addAttribute("taxpayer", res);
        return "table-edit";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/table/{id}/edit")
    public String tablePostEdit(@PathVariable(value = "id") long taxpayerId, @RequestParam String name,
                                   @RequestParam String surname, @RequestParam String secname, Model model) throws SQLException {
        TaxpayerEntity taxpayer = taxpayerRepository.findById(taxpayerId).orElseThrow();
        taxpayer.setName(name);
        taxpayer.setSurname(surname);
        taxpayer.setSecname(secname);
        taxpayerRepository.save(taxpayer);
        return "redirect:/table";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/table/{id}/remove")
    public String tablePostRemove(@PathVariable(value = "id") long taxpayerId, Model model) throws SQLException {
        TaxpayerEntity taxpayer = taxpayerRepository.findById(taxpayerId).orElseThrow();
        taxpayerRepository.delete(taxpayer);
        return "redirect:/table";
    }
}
