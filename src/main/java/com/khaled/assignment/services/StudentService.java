package com.khaled.assignment.services;

import com.khaled.assignment.models.Student;
import com.khaled.assignment.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return this.studentRepository.findAll();
    }

    public Student getStudentById(Long id){
         return (Student) this.studentRepository.findAllById(Collections.singleton(id));
    }

    public Student addStudent(Student student){
        this.studentRepository.save(student);
        return student;
    }

    public void deleteStudent(Long id){
        this.studentRepository.deleteAllById(Collections.singleton(id));
    }

    public Student updateStudent(Long id, Student student){
        student.setId(id);
        this.studentRepository.save(student);
        return student;
    }
}
