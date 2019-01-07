package com.jurkiewicz.grzegorz.FlatApp2.controller;

import com.jurkiewicz.grzegorz.FlatApp2.model.Invoice;
import com.jurkiewicz.grzegorz.FlatApp2.model.MediaAndService;
import com.jurkiewicz.grzegorz.FlatApp2.model.User;
import com.jurkiewicz.grzegorz.FlatApp2.repository.InvoiceRepository;
import com.jurkiewicz.grzegorz.FlatApp2.repository.MediaAndServiceRepository;
import com.jurkiewicz.grzegorz.FlatApp2.repository.UserRepository;
import com.jurkiewicz.grzegorz.FlatApp2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AuthenticationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    MediaAndServiceRepository mediaAndServiceRepository;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        MediaAndService mediaAndService = new MediaAndService();
        String name = principal.getName();
        User us = userService.getActiveUser(name);
        modelAndView.addObject("auth_user", us);
        modelAndView.addObject("mediaAndService", mediaAndService);
        modelAndView.addObject("mediaAndService", mediaAndServiceRepository.findAll());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView addProduct(@Valid MediaAndService mediaAndService, BindingResult bindingResult, Principal principal, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);

        }
        String name = principal.getName();
        User us = userService.getActiveUser(name);

        us.getMediaAndServiceList().add(mediaAndService);
        modelAndView.addObject("user", us);
        modelAndView.addObject("auth_user", us);
        modelAndView.addObject("success", "The product has been successfully bought.");
        modelAndView.addObject("products", mediaAndServiceRepository.findAll());
        modelAndView.setViewName("home");
        int sum = 0;
        for (MediaAndService p : us.getMediaAndServiceList()) {
            System.out.println(p);
            sum += p.getFee();
            System.out.println(sum);
        }
return modelAndView;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);

//        } else if (userService.isUserAlreadyPresent(user)){
//            modelAndView.addObject("successMessage", "User already exists!");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "You have been registered successfully!");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }
    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin");
        return modelAndView;
    }




}