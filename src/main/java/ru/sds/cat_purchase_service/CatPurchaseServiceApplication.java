package ru.sds.cat_purchase_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.sds.cat_purchase_service.config.RabbitMqConfig;

@SpringBootApplication
public class CatPurchaseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatPurchaseServiceApplication.class, args);
    }

}
