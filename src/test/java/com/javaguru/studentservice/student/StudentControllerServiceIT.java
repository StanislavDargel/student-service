package com.javaguru.studentservice.student;

import com.javaguru.studentservice.student.dto.StudentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerServiceIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Test
    void shouldFindStudentById() throws Exception {
        StudentResponse response = studentResponse();
        when(service.findById(response.getId())).thenReturn(response);

        mockMvc.perform(get("/students/" + response.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.lastName").value(response.getLastName()))
                .andExpect(jsonPath("$.quote").value(response.getQuote()));
    }

    private StudentResponse studentResponse() {
        return new StudentResponse(
                UUID.randomUUID().toString(),
                "Nyam-nyam",
                "Bayam-Bayam",
                "MEGA_QUOTE"
        );
    }

}
