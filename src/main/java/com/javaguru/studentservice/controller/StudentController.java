package com.javaguru.studentservice.controller;

import com.javaguru.studentservice.dto.StudentDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public List<StudentDto> findAllStudents() {
        List<StudentDto> studentDtos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            studentDtos.add(new StudentDto(UUID.randomUUID().toString(),
                    "STUDENT_NAME_" + i,
                    "STUDENT_LAST_NAME_" + i));
        }
        return studentDtos;
    }

    @GetMapping("/{id}")
    public StudentDto findStudentById(@PathVariable String id) {
        System.out.println("Received find student by id request, id: " + id);
        return new StudentDto(id,
                "STUDENT_NAME",
                "STUDENT_LAST_NAME");
    }

    @PostMapping
    public void createStudent(@RequestBody StudentDto studentDto) {
        System.out.println("Received request create student: " + studentDto);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable String id, @RequestBody StudentDto studentDto) {
        System.out.println("Received id: " + id);
        System.out.println("Received request update student: " + studentDto);
    }
}
