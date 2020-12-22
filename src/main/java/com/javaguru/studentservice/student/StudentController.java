package com.javaguru.studentservice.student;

import com.javaguru.studentservice.student.dto.StudentCreateRequest;
import com.javaguru.studentservice.student.dto.StudentResponse;
import com.javaguru.studentservice.student.dto.StudentUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentResponse> findAllStudents() {
        logger.info("Received find all Students request");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StudentResponse findStudentById(@PathVariable String id) {
        logger.info("Received find student by id request, id: {}", id);
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Validated @RequestBody StudentCreateRequest request,
                                                         UriComponentsBuilder builder) {
        logger.info("Received create student request: {}", request);
        StudentResponse response = service.save(request);
        return ResponseEntity.created(builder.path("/students/{id}")
                .buildAndExpand(response.getId()).toUri()).body(response);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateStudent(@PathVariable String id, @Validated @RequestBody StudentUpdateRequest updateRequest) {
        logger.info("Received request update student: {}", updateRequest);
        service.update(id, updateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        logger.info("Received delete student by id {}", id);
        service.deleteStudentById(id);
    }
}

