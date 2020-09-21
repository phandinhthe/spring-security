package com.terry.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> students = Arrays.asList(
            new Student(1, "The Phan"),
            new Student(2, "The Trinh"),
            new Student(3, "The Nguyen")
    );
    @GetMapping(path = "/{id}")
    public Student get(@PathVariable("id") Integer id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Student %d cannot be found", id.intValue())));
    }

}
