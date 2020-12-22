package com.javaguru.studentservice.student;

import com.javaguru.studentservice.quote.QuoteDto;
import com.javaguru.studentservice.student.domain.StudentEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class StudentControllerRepositoryIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository repository;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void shouldFindStudentById() throws Exception {
        StudentEntity entity = studentEntity("Name1");
        repository.save(entity);

        mockMvc.perform(get("/students/" + entity.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(entity.getId()))
                .andExpect(jsonPath("$.name").value(entity.getName()))
                .andExpect(jsonPath("$.lastName").value(entity.getLastName()))
                .andExpect(jsonPath("$.quote").value(entity.getQuote()));
    }

    @Test
    void shouldFindAllStudents() throws Exception {
        StudentEntity firstStudent = studentEntity("Name1");
        repository.save(firstStudent);
        StudentEntity secondStudent = studentEntity("Name2");
        repository.save(secondStudent);

        mockMvc.perform(get("/students/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(firstStudent.getId()))
                .andExpect(jsonPath("$[1].id").value(secondStudent.getId()));
    }

    @Test
    void shouldCreateStudent() throws Exception {
        when(restTemplate.getForObject(anyString(), eq(QuoteDto.class))).thenReturn(quoteDto());
        mockMvc.perform(post("/students")
                .content(createStudentRequestJson())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Shlepa"))
                .andExpect(jsonPath("$.lastName").value("Morkovkin"))
                .andExpect(jsonPath("$.quote").value("I am happy Quote"))
                .andExpect(header().exists("Location"));
    }

    private StudentEntity studentEntity(String name) {
        StudentEntity entity = new StudentEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setQuote("QUOTE");
        entity.setName(name);
        entity.setLastName("LAST_NAME");
        return entity;
    }

    private String createStudentRequestJson() throws JSONException {
        return new JSONObject()
                .put("name", "Shlepa")
                .put("lastName", "Morkovkin")
                .toString();
    }

    private QuoteDto quoteDto() {
        QuoteDto quote = new QuoteDto();
        quote.setAuthor("Me");
        quote.setExternalId("123");
        quote.setSecondExternalId("321");
        quote.setQuote("I am happy Quote");
        return quote;
    }

}
