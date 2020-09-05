package com.javaguru.studentservice.student;

import com.javaguru.studentservice.mapper.BeanMapper;
import com.javaguru.studentservice.quote.QuoteDto;
import com.javaguru.studentservice.quote.QuoteService;
import com.javaguru.studentservice.student.domain.StudentEntity;
import com.javaguru.studentservice.student.dto.StudentCreateRequest;
import com.javaguru.studentservice.student.dto.StudentResponse;
import com.javaguru.studentservice.student.dto.StudentUpdateRequest;
import com.javaguru.studentservice.student.validation.StudentNotFoundException;
import com.javaguru.studentservice.student.validation.StudentValidationMSG;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final BeanMapper mapper;
    private final QuoteService quoteService;

    public StudentService(StudentRepository repository, BeanMapper mapper, QuoteService quoteService) {
        this.repository = repository;
        this.mapper = mapper;
        this.quoteService = quoteService;
    }

    public StudentResponse save(StudentCreateRequest request) {
        QuoteDto quote = quoteService.findRandomQuote();
        StudentEntity studentEntity = mapper.toEntity(request, quote.getQuote());
        repository.save(studentEntity);

        return new StudentResponse(studentEntity.getId(),
                studentEntity.getName(),
                studentEntity.getLastName(),
                studentEntity.getQuote());
    }

    public StudentResponse findById(String id) {
        return repository.findById(id)
                .map(studentEntity -> new StudentResponse(studentEntity.getId(),
                        studentEntity.getName(),
                        studentEntity.getLastName(),
                        studentEntity.getQuote()))
                .orElseThrow(() -> new StudentNotFoundException(StudentValidationMSG.STUDENT_NOT_FOUND_BY_ID + id));
    }

    public void deleteStudentById(String id) {
        if (!repository.existsById(id)) {
            throw new StudentNotFoundException(StudentValidationMSG.STUDENT_NOT_FOUND_BY_ID + id);
        }
        repository.deleteById(id);
    }

    public List<StudentResponse> findAll() {
        return repository.findAll().stream()
                .map(studentEntity -> new StudentResponse(studentEntity.getId(),
                        studentEntity.getName(),
                        studentEntity.getLastName(),
                        studentEntity.getQuote()))
                .collect(Collectors.toList());
    }

    public void update(String id, StudentUpdateRequest updateRequest) {
        StudentEntity foundedStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(StudentValidationMSG.STUDENT_NOT_FOUND_BY_ID + id));
        foundedStudent.setName(updateRequest.getName());
        foundedStudent.setLastName(updateRequest.getLastName());
        repository.save(foundedStudent);
    }
}
