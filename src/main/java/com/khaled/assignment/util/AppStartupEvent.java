package com.khaled.assignment.util;

import com.khaled.assignment.models.Student;
import com.khaled.assignment.services.StudentService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final StudentService studentService;

    public AppStartupEvent(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        print("Connected");
    }

    public static void  print(Object o){
        System.out.println(o);
    }
}