package com.cmb.startup;


import com.cmb.startup.service.BUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
        /*(exclude={DataSourceAutoConfiguration.class})*/
public class Application {

    public static void main(String args[])  throws Exception  {
        SpringApplication.run(Application.class, args);

//        new BUserService().buildSoud(null);
    }
}
