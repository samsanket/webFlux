package com.sanketdd.webFlux.handler;

import com.sanketdd.webFlux.doa.CustomerDoa;
import com.sanketdd.webFlux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDoa customerDoa;

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customerFlux=customerDoa.loadCustomerInStreamFlux();
        return ServerResponse.ok().body(customerFlux,Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest serverRequest) {
       int psthvar= Integer.valueOf(serverRequest.pathVariable("input"));
      Mono<Customer> customerMono= customerDoa.loadCustomerInStream().filter(customer -> customer.getId()==psthvar).next();
      return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> addCustomer(ServerRequest serverRequest) {
         Mono<Customer> customer=serverRequest.bodyToMono(Customer.class);
      Mono<String> stringMono =  customer.map(dto -> dto.getId()+ " :::::  " + dto.getName());
      return ServerResponse.ok().body(stringMono,String.class);
    }
}
