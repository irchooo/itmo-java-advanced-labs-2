package ru.itmo.javaadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmo.javaadvanced.dto.ExampleDto;

@SpringBootApplication
public class ApplicationRunner {
    public static void main(String[] args) {
        ExampleDto exampleDto = new ExampleDto();
        exampleDto.setId(1L);
        System.out.println(exampleDto.getId());

        SpringApplication.run(ApplicationRunner.class, args);
    }

}
