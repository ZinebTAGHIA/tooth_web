package ma.taghia.fmdc_backend.controllers;


import ma.taghia.fmdc_backend.entities.Professor;
import ma.taghia.fmdc_backend.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Professor professorDetails) {
        Professor professor = professorService.create(professorDetails);
        if (professor == null) {
            return new ResponseEntity<>("Invalid request Data", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Professor professor = professorService.findById(id);
        if (professor == null) {
            return new ResponseEntity<>("Professor not found", HttpStatus.NOT_FOUND);
        } else {
            boolean deleted = professorService.delete(professor);
            if (deleted) {
                return new ResponseEntity<>("Professor deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Professor associated to a user", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Professor professor) {
        if (professorService.findById(id) == null) {
            return new ResponseEntity<>("Professor not found", HttpStatus.NOT_FOUND);
        } else {
            professor.setId(id);
            return new ResponseEntity<>(professorService.update(professor), HttpStatus.OK);
        }
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Professor>> findAll() {
        return new ResponseEntity<>(professorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Professor professor = professorService.findById(id);
        if (professor == null) {
            return new ResponseEntity<>("Professor not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        }
    }
}

