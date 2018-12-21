package com.codegym.cms.controllers;

import com.codegym.cms.model.Category;
import com.codegym.cms.model.User;
import com.codegym.cms.service.CategoryService;
import com.codegym.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping(value = "/blog")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("category")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }



    @GetMapping(value = "list")
    public ModelAndView showList(@PageableDefault(size = 10) Pageable pageable){
        Page<User> list = userService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/user/list");
        modelAndView.addObject("list", list);
        return modelAndView;
    }
    @GetMapping(value = "list-sort")
    public ModelAndView sortUser(@PageableDefault(size = 10, direction = Sort.Direction.DESC, sort = "id") Pageable pageable ){
        Page<User> list = userService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/user/list-sort");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @GetMapping("/list-title")
    public ModelAndView showCustomers(Pageable pageable, @RequestParam("s") Optional<String> s,@RequestParam("s") String a){
        Page<User> list = userService.findAllByTitle(s.get(), pageable);
        ModelAndView modelAndView = new ModelAndView("/user/list-title");
        modelAndView.addObject("list", list);
        modelAndView.addObject("s",a);
        return modelAndView;
    }

    @GetMapping(value = "create")
    public ModelAndView showCreate(){
        return new ModelAndView("/user/create", "user", new User());
    }
    @PostMapping(value = "create")
    public ModelAndView createUser(@ModelAttribute("user") User user){
        userService.save(user);
        return new ModelAndView("/user/create", "message", "Created user success!");
    }
    @GetMapping(value = "edit/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
        User user = userService.findById(id);
        if(user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/edit");
            modelAndView.addObject("user", user);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping(value = "edit")
    public ModelAndView editUser(@ModelAttribute("user") User user){
        userService.save(user);
        return new ModelAndView("/user/edit", "message", "Edited blog success!");
    }
    @GetMapping(value = "view/{id}")
    public ModelAndView showView(@PathVariable long id){
        User user = userService.findById(id);
        ModelAndView model = new ModelAndView("/user/view", "user", user);
        return model;
    }
    @GetMapping(value = "delete/{id}")
    public ModelAndView showDelete(@PathVariable long id){
        User user = userService.findById(id);
        ModelAndView model = new ModelAndView("/user/delete", "user", user);
        return model;
    }
    @PostMapping(value = "delete")
    public String deleteUser(@ModelAttribute("user") User user){
        userService.remove(user.getId());
        return "redirect:/blog/list";
    }

    @GetMapping("/view-category/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        if(category == null){
            return new ModelAndView("/error.404");
        }

        Iterable<User> users = userService.findAllByCategory(category);

        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category);
        modelAndView.addObject("user", users);
        return modelAndView;
    }
}
