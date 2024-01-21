package com.sanketdd.webFlux.doa;


import com.sanketdd.webFlux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDoa {
    public List<Customer> getCustomer() {


        return IntStream.rangeClosed(1, 50).peek(i -> System.out.println("processing count :" + i))
                .mapToObj(i -> new Customer(i, "customer  :" + i))
                .collect(Collectors.toList());


    }

    public List<Customer> getCustomerDelay(){
        return IntStream.rangeClosed(1,50)
                .peek(CustomerDoa::SleepProcess)
                .peek(i -> System.out.println("processing count :" + i))
                .mapToObj(i->new Customer(i,"customer"+i))
                .collect(Collectors.toList());
    }

    private static void SleepProcess(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    public Flux<Customer> loadCustomerInStream(){
        return Flux.range(1,50)
                .delayElements(Duration.ofSeconds(1))
                .map(i->new Customer(i,"customer"+i))
                .doOnNext(i->System.out.println("Porcessig :"+i));
    }
}
