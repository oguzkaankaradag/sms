package com.example.springBootTesting.service;


import com.example.springBootTesting.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student createStudent(Student student);
    void deleteStudent(Long id);
    void deleteAllStudents();

}
