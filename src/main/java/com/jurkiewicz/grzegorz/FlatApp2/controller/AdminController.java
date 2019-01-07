package com.jurkiewicz.grzegorz.FlatApp2.controller;

import com.jurkiewicz.grzegorz.FlatApp2.model.Invoice;
import com.jurkiewicz.grzegorz.FlatApp2.model.MediaAndService;
import com.jurkiewicz.grzegorz.FlatApp2.repository.InvoiceRepository;
import com.jurkiewicz.grzegorz.FlatApp2.repository.MediaAndServiceRepository;
import com.jurkiewicz.grzegorz.FlatApp2.repository.UserRepository;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    MediaAndServiceRepository mediaAndServiceRepository;

//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public ModelAndView adminHome() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin");
//        return modelAndView;
//    }

    @GetMapping("/admin/users")
    public String postsPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/admin/invoices")
    public String invoicesPage(Model model) {
        model.addAttribute("invoices", invoiceRepository.findAll());
        return "invoices";
    }

//    @GetMapping("/admin/invoices/{id}")
//    public String getNoteById(@PathVariable(value = "id") Long invoiceId) {
//        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new ResourceClosedException("id"));
//        invoice.setStatus(OrderUtils.COMPLETE.toString());
//        System.out.println(invoice.getStatus());
//        invoiceRepository.save(invoice);
//        return "invoices";
//    }


    @PostMapping("/admin/add")
    public String addProduct(@RequestParam(value = "name") String name,
                             @RequestParam(value = "price") int price, Model model) {
        model.addAttribute("success", "The product has been successfully entered.");
        MediaAndService mediaAndService = new MediaAndService(name, price);
        mediaAndServiceRepository.save(mediaAndService);
        return "admin";
    }
}