package com.khaled.assignment.controllers;

import com.khaled.assignment.models.Student;
import com.khaled.assignment.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents(){
        return this.studentService.getStudents();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable(value="id") Long id){
        return this.studentService.getStudentById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Student newStudent(@RequestBody Student student){
        return this.studentService.addStudent(student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable(value="id") Long id){
        this.studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@PathVariable(value = "id") Long id, @RequestBody Student student){
        return this.studentService.updateStudent(id, student);
    }
}
