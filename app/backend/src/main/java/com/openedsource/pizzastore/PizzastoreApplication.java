package com.openedsource.pizzastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication
@ComponentScan(
        basePackages = {"com.openedsource.pizzastore"},
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@EnableCaching
public class PizzastoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(PizzastoreApplication.class, args);
    }
}
