package com.project.hb.billing;

import com.project.hb.billing.domain.message.BillingMessageChannels;
import com.project.hb.billing.domain.message.EventListener;
import jdk.jfr.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = {"com.project.hb.billing", "com.project.hb.booking"})
@EnableBinding({BillingMessageChannels.class})
public class BillingApp {

    public static void main(String[] args) {
        SpringApplication.run(BillingApp.class, args);
    }

    @Bean
    public EventListener eventListener() {
        return new EventListener();
    }

    // config event listener

}
