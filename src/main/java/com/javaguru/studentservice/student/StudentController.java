package com.javaguru.studentservice.student;

import com.javaguru.studentservice.student.dto.StudentCreateRequest;
import com.javaguru.studentservice.student.dto.StudentResponse;
import com.javaguru.studentservice.student.dto.StudentUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentResponse> findAllStudents() {
        System.out.println("Received find all Student");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StudentResponse findStudentById(@PathVariable String id) {
        System.out.println("Received find student by id request, id: " + id);
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Validated @RequestBody StudentCreateRequest request,
                                                         UriComponentsBuilder builder) {
        System.out.println("Received create student request: " + request);
        StudentResponse response = service.save(request);
        return ResponseEntity.created(
                builder.path("/students/{id}")
                        .buildAndExpand(response.getId()).toUri()).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateStudent(@PathVariable String id, @Validated @RequestBody StudentUpdateRequest updateRequest) {
        System.out.println("Received id: " + id + ". Received request update student: " + updateRequest);
        service.updateStudentParameters(id, updateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("Received delete student by id " + id);
        service.deleteStudentById(id);
    }
}

