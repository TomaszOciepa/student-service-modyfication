package com.mango.studentService.controller;

import com.mango.studentService.model.Status;
import com.mango.studentService.model.Student;
import com.mango.studentService.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    //    @RequestParam(required = false) - parametr nie jest wtymagany
    @RolesAllowed("ROLE_ADMIN")
    @GetMapping
    public List<Student> getStudents(@RequestParam(required = false) Status status) {
        return studentService.getStudents(status);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/emails")
    List<Student> getStudentsByEmails(@RequestBody List<String> emails) {
        return studentService.getStudentsByEmails(emails);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        return studentService.getStudent(id);
    }

    @RolesAllowed({"ROLE_STUDENT"})
    @GetMapping("/me")
    public Student getStudent(Principal user) {
        return studentService.getStudent(user);
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }

    @RolesAllowed({"ROLE_STUDENT", "ROLE_ADMIN"})
    @PutMapping("/{id}")
    public Student putStudent(@PathVariable String id, @Valid @RequestBody Student student) {
        return studentService.putStudent(id, student);
    }

    @RolesAllowed({"ROLE_STUDENT", "ROLE_ADMIN"})
    @PatchMapping("/{id}")
    public Student patchStudent(@PathVariable String id, @Valid @RequestBody Student student) {
        return studentService.patchStudent(id, student);
    }

}
