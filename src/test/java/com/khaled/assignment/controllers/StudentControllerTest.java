package com.khaled.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khaled.assignment.LearningSpringApplication;
import com.khaled.assignment.models.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LearningSpringApplication.class)
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private StudentController studentController;

    @Test
    public void testGetAllStudents() throws Exception {
        Student student = getStudent();
        List<Student> students = new ArrayList<>();
        students.add(student);
        given(studentController.getAllStudents()).willReturn(students);
        mvc.perform(get("/api/student/").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(student.getName())));
    }

    @Test
    public void testGetStudent() throws Exception {
        Student student = getStudent();
        given(studentController.getStudent(1L)).willReturn(student);
        mvc.perform(get("/api/student/"+student.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(student.getName())));
    }

    @Test
    public void testAddStudent() throws Exception{
        Student student = getStudent();
        given(studentController.newStudent(student)).willReturn(student);
        mvc.perform(post("/api/student/").content(asJson(student)).contentType(APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void testDeleteStudent() throws Exception{
        Student student = getStudent();
        doNothing().when(studentController).deleteStudent(1L);
        mvc.perform(delete("/api/student?id=" + student.getId()).content(asJson(student)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void testUpdateStudent() throws Exception{
        Student student = getStudent();
        given(studentController.newStudent(student)).willReturn(student);
        mvc.perform(put("/api/student?id=" + student.getId()).content(asJson(student)).contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }



    private Student getStudent(){
        Student student = new Student();
        student.setId(1);
        student.setName("Samir William");
        student.setAge(56);
        student.setDepartment("Hair");
        return student;
    }

    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
