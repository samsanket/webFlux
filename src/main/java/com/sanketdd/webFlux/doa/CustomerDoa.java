package com.sanketdd.webFlux.doa;


import com.sanketdd.webFlux.dto.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDoa {
public List<Customer> getCustomer(){


    return IntStream.rangeClosed(1,50)

//            .peek(i->System.out.println("processing count :"+i))

            .mapToObj(i->new Customer(i,"customer  :"+i))

            .collect(Collectors.toList());


}
}
