package com.example.ticket;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class TicketProviderApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(TicketProviderApplication.class);
    }
}
