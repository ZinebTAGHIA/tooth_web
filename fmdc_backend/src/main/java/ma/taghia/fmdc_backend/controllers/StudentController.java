package ma.taghia.fmdc_backend.controllers;


import ma.taghia.fmdc_backend.entities.Student;
import ma.taghia.fmdc_backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private StudentService studentService;

    private final BCryptPasswordEncoder passwordEncoder;

    public StudentController(StudentService studentService, BCryptPasswordEncoder passwordEncoder) {
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Student studentDetails) {
        String hashedPassword = passwordEncoder.encode(studentDetails.getPassword());
        studentDetails.setPassword(hashedPassword);
        Student student = studentService.create(studentDetails);
        if (student == null) {
            return new ResponseEntity<>("Invalid request Data", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } else {
            boolean deleted = studentService.delete(student);
            if (deleted) {
                return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student associated to a user", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Student student) {
        if (studentService.findById(id) == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } else {
            student.setId(id);
            return new ResponseEntity<>(studentService.update(student), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }
}

