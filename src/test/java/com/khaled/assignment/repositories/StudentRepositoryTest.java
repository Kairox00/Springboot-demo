package com.khaled.assignment.repositories;

import com.khaled.assignment.LearningSpringApplication;
import com.khaled.assignment.models.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = LearningSpringApplication.class)
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindById(){
        Student student = getStudent();
        studentRepository.save(student);
        Student result = studentRepository.findById(student.getId()).get();
        assertEquals(student.getId(), result.getId());
    }

    @Test
    public void testFindAll(){
        Student student = getStudent();
        studentRepository.save(student);
        List<Student> result = studentRepository.findAll();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testSave() {
        Student student = getStudent();
        studentRepository.save(student);
        Student found = studentRepository.findById(student.getId()).get();
        assertEquals(found.getId(), student.getId());
    }

    @Test
    public void testDelete(){
        Student student = getStudent();
        studentRepository.save(student);
        studentRepository.deleteById(student.getId());
        List<Student> result = studentRepository.findAll();
        assertEquals(result.size(), 0);

    }

    private Student getStudent(){
        Student student = new Student();
        student.setId(1);
        student.setName("Samir William");
        student.setAge(56);
        student.setDepartment("Hair");
        return student;
    }
}
