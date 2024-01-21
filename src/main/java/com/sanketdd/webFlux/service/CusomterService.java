package com.sanketdd.webFlux.service;

import com.sanketdd.webFlux.doa.CustomerDoa;
import com.sanketdd.webFlux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CusomterService {
    @Autowired
    private CustomerDoa customerDoa;


    public List<Customer> loadAllCustomer() {
        long startTime = System.currentTimeMillis();
        List<Customer> listCustomer = customerDoa.getCustomer();
        Long endtime = System.currentTimeMillis();
        printTime(startTime, endtime);
        return listCustomer;
    }

    private static void printTime(long startTime, Long endtime) {
        System.out.println("Total Execution time Start time : " + startTime + "End time :" + endtime);
        System.out.println("Total Execution time " + (endtime - startTime));
    }


    public List<Customer> getAllCustomerDelay() {
        long startTime = System.currentTimeMillis();
        List<Customer> listCustomer = customerDoa.getCustomerDelay();
        Long endtime = System.currentTimeMillis();
        printTime(startTime, endtime);
        return listCustomer;
    }


    public Flux<Customer> loadAllCustomerInStream() {
        long startTime = System.currentTimeMillis();
        Flux<Customer> listCustomer = customerDoa.loadCustomerInStream();
        Long endtime = System.currentTimeMillis();
        printTime(startTime, endtime);
        return listCustomer;
    }
}
