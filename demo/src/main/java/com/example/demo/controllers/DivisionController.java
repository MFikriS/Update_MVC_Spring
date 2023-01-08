package com.example.demo.controllers;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.daos.DivisionDao;
import com.example.demo.models.Division;
import com.example.demo.tools.DBConnection;

@Controller
@RequestMapping("division")
public class DivisionController {
    private DivisionDao dDao = new DivisionDao(DBConnection.getConnection());

    @GetMapping
    public String index(Model model) {
        // Division object = new Division();
        model.addAttribute("divisions", dDao.getAll());
        return "division/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String create(@PathVariable (required = false) Integer id, Model model){
        if(id != null){
            model.addAttribute("division", dDao.getById(id));
        } else {
            model.addAttribute("division", new Division());
            model.addAttribute("division", dDao.getNameRegion());
        }
        return "division/form";
    }

    /*
    @GetMapping(value = {"form"})
    public String create(Model model){
            model.addAttribute("division", new Division());
        return "division/form";
    }
    */

    //DELETE
    @PostMapping(value = {"delete/{id}"})
    public  String delete(@PathVariable Integer id){
        Boolean result = dDao.deleteData(id);
        if(result){
            return "redirect:/division";
        } else {
            return "Failed Delete";
        }
    }

    /*
    @GetMapping(value = {"form/{id}"})
    public String create(@PathVariable(required = false) Integer id, Model model){
        model.addAttribute("division", dDao.getById(id));
        return "division/form";
    }
    */

    @PostMapping("save")
    public String save(@Nullable Division division){
        Boolean result;
        if(division.getId() != null){
            result = dDao.updateData(division);
        } else {
            result = dDao.insertData(division);
        }
        if(result){
            return "redirect:/division";
        } else {
            return "division/form";
        }
    }
}
