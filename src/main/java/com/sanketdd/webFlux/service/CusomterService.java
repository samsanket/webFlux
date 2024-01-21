package com.sanketdd.webFlux.service;

import com.sanketdd.webFlux.doa.CustomerDoa;
import com.sanketdd.webFlux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CusomterService {
    @Autowired
    private CustomerDoa customerDoa;


    public List<Customer> loadAllCustomer() {
        long startTime = System.currentTimeMillis();
        List<Customer> listCustomer = customerDoa.getCustomer();
        Long endtime = System.currentTimeMillis();
        System.out.println("Total Execution time Start time : " + startTime + "End time :" + endtime);
        System.out.println("Total Execution time " + (endtime - startTime));
        return listCustomer;
    }


    public List<Customer> getAllCustomerDelay() {
        long startTime = System.currentTimeMillis();
        List<Customer> listCustomer = customerDoa.getCustomerDelay();
        Long endtime = System.currentTimeMillis();
        System.out.println("Total Execution time Start time : " + startTime + "End time :" + endtime);
        System.out.println("Total Execution time " + (endtime - startTime));
        return listCustomer;
    }


}
