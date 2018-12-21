package com.codegym.cms.controllers;

import com.codegym.cms.model.Category;
import com.codegym.cms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/category")
    public ModelAndView showList(){
        Iterable<Category> categories = categoryService.findAll();
        return new ModelAndView("/category/list","category",categories);
    }
    @GetMapping(value = "/create")
    public ModelAndView showCreate(){
        return new ModelAndView("/category/create","category", new Category());
    }
    @PostMapping(value = "/create")
    public ModelAndView create(@ModelAttribute("category") Category category){
        categoryService.save(category);
        return new ModelAndView("/category/create", "message","Created category success!");
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView showEdit(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        return new ModelAndView("/category/edit", "category",category);
    }
    @PostMapping(value = "/edit")
    public ModelAndView edit(@ModelAttribute("category") Category category){
        categoryService.save(category);
        return new ModelAndView("/category/edit", "message", "Edited category success");
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
        Category category =categoryService.findById(id);
        return new ModelAndView("/category/delete", "category",category);
    }
    @PostMapping(value = "/delete")
    public ModelAndView delete(@ModelAttribute("category") Category category){
        categoryService.delete(category.getId());
        return new ModelAndView("redirect:/category");
    }
}
