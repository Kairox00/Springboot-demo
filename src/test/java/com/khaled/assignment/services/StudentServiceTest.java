package com.khaled.assignment.services;

import com.khaled.assignment.LearningSpringApplication;
import com.khaled.assignment.models.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LearningSpringApplication.class)
public class StudentServiceTest {
    @MockBean
    private StudentService studentService;

    @Test
    public void testGetAllStudents(){
        Student student = getStudent();
        List<Student> students = new ArrayList<>();
        students.add(student);
        given(studentService.getStudents()).willReturn(students);
        List<Student> result = studentService.getStudents();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testGetStudent(){
        Student student = getStudent();
        given(studentService.getStudentById(1L)).willReturn(student);
        Student result = studentService.getStudentById(1L);
        assertEquals(result.getId(), 1);
    }

    @Test
    public void testDeleteStudent(){
        Student student = getStudent();
        doNothing().when(studentService).deleteStudent(1L);
        studentService.deleteStudent(1L);
        assertTrue(true);
    }

    @Test
    public void testSaveStudent(){
        Student student = getStudent();
        given(studentService.updateStudent(1L, student)).willReturn(student);
        studentService.updateStudent(1L, student);
        assertTrue(true);
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
