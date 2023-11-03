package com.ethnocopia;
import java.util.List;
import java.util.Optional;

import com.ethnocopia.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication

public class Main {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static  void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


}
