package com.terry.security.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    private static final List<Student> students = Arrays.asList(
            new Student(1, "The Phan"),
            new Student(2, "The Trinh"),
            new Student(3, "The Nguyen")
    );

    @PostMapping
    public ResponseEntity<?> register(@RequestBody Student student) {
        System.out.println("Register new student successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        System.out.println("Delete student successfully");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity update(@PathVariable("id") Integer id ,@RequestBody Student student) {
        System.err.println("Update student successfully");
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping
    public ResponseEntity<?> get() {
        System.err.println("Retrieve all students successfully");
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }
}
