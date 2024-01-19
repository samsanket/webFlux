package com.sanketdd.webFlux.controller;

import com.sanketdd.webFlux.dto.Customer;
import com.sanketdd.webFlux.service.CusomterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CusomterService service;

    @GetMapping("/")
    public List<Customer> getAll(){
       return service.loadAllCustomer();
    }
}
