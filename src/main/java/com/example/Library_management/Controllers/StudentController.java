package com.example.Library_management.Controllers;

import com.example.Library_management.Models.Student;
import com.example.Library_management.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String createStudent(@RequestBody Student student){
        return  studentService.createStudent(student);
    }
    @PutMapping("/update_mail")
    public String updateMail(@RequestParam String email,@RequestParam int studentId){
        return studentService.updateMail(email,studentId);
    }
}
