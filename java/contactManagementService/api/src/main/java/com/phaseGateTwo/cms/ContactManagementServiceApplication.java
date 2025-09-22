package com.phaseGateTwo.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.phaseGateTwo.cms")
public class ContactManagementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContactManagementServiceApplication.class, args);
    }
}