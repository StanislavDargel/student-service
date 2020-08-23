package com.javaguru.studentservice.student;

import com.javaguru.studentservice.mapper.BeanMapper;
import com.javaguru.studentservice.student.domain.StudentEntity;
import com.javaguru.studentservice.student.dto.StudentCreateRequest;
import com.javaguru.studentservice.student.dto.StudentResponse;
import com.javaguru.studentservice.student.dto.StudentUpdateRequest;
import com.javaguru.studentservice.student.validation.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final BeanMapper mapper;

    public StudentService(StudentRepository repository, BeanMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public StudentResponse save(StudentCreateRequest request) {
        StudentEntity studentEntity = mapper.toEntity(request);
        repository.save(studentEntity);

        return new StudentResponse(studentEntity.getId(), studentEntity.getName(), studentEntity.getLastName());
    }

    public StudentResponse findById(String id) {
        return repository.findById(id)
                .map(studentEntity -> new StudentResponse(studentEntity.getId(), studentEntity.getName(), studentEntity.getLastName()))
                .orElseThrow(() -> new StudentNotFoundException("Student not found, id: " + id));
    }

    public void deleteStudentById(String id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException("Student not found, id: " + id);
        }
        repository.deleteById(id);
    }

    public List<StudentResponse> findAll() {
        return repository.findAll().stream()
                .map(studentEntity -> new StudentResponse(studentEntity.getId(),
                        studentEntity.getName(),
                        studentEntity.getLastName()))
                .collect(Collectors.toList());
    }

    public void updateStudentParameters(String id, StudentUpdateRequest updateRequest) {
        StudentEntity foundedStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found, id: " + id));
        foundedStudent.setName(updateRequest.getName());
        foundedStudent.setLastName(updateRequest.getLastName());
        repository.save(foundedStudent);
    }
}
