package org.cs309.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.*;
import org.springframework.context.annotation.ComponentScan;
/**
 *The application itself. Currently process no command line arguments. You can invoke this application by 
 *running 
 *<p>mvn spring-boot:run</p>
 *in the directory containing pom.xml
 *@author Joshua Kalyanapu
 */

@SpringBootApplication
@EnableScheduling
public class Main {
    public static void main(String[] args) {
	SpringApplication.run(Main.class, args);
    }
}
