package com.sanketdd.webFlux.router;

import com.sanketdd.webFlux.handler.CustomerHandler;
import com.sanketdd.webFlux.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler customerHandler;

    @Autowired
    CustomerStreamHandler customerStreamHandler;
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers",customerHandler::loadCustomers)
                .GET("/router/customer/stream",customerStreamHandler::loadCustomersStream)
                .GET("/router/cutomer/{input}",customerHandler::findCustomer)
                .POST("/router/customer/save",customerHandler::addCustomer)
                .build();
    }

}
