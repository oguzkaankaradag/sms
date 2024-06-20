package com.example.springBootTesting.integration;

import com.example.springBootTesting.model.Student;
import com.example.springBootTesting.repository.StudentRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerITests extends AbstractContainerBaseTest{

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    //given/when/then format - BDD style
    @Test
    @DisplayName("JUnit I Test for getAll students REST API")
    void givenStudents_whenGetAllStudents_thenListOfStudents() throws Exception {
        //given
        List<Student> students = List.of(Student.builder()
                        .firstName("oguz")
                        .lastName("karadag")
                        .email("karadagoguzkaan@gmail.com")
                        .build(),
                Student.builder()
                        .firstName("selcuk")
                        .lastName("karadag")
                        .email("karadagselcuk@gmail.com")
                        .build());

        studentRepository.saveAll(students);

        //when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/students"));

        //then
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(students.size())));
    }
}
