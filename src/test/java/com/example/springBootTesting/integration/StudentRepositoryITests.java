package com.example.springBootTesting.integration;

import com.example.springBootTesting.model.Student;
import com.example.springBootTesting.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // in memory db wont be used
public class StudentRepositoryITests extends AbstractContainerBaseTest{

    @Autowired
    private StudentRepository studentRepository;


    //JUnit test case for save student operation - BDD style
    @Test
    public void givenStudentObject_whenSave_thenReturnSavedStudent(){
        //given
        Student student = Student.builder()
                .firstName("oguz")
                .lastName("karadag")
                .email("karadagoguzkaan@gmail.com")
                .build();

        //when
        Student savedStudent = studentRepository.save(student);

        //then
        Assertions.assertNotNull(savedStudent);
        Assertions.assertNotNull(savedStudent.getId());

    }

    //JUnit test case for getStudentById operation - BDD style
    @Test
    public void givenStudentId_whenFindById_thenReturnSavedStudent(){
        //given
        Student student = Student.builder()
                .firstName("oguz")
                .lastName("karadag")
                .email("karadagoguzkaan@gmail.com")
                .build();
        
        Student savedStudent = studentRepository.save(student);

        //when
        Student studentWithId = studentRepository.findById(savedStudent.getId()).get();
        //then
        Assertions.assertNotNull(studentWithId);

    }
}
