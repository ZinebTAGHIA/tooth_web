package ma.taghia.fmdc_backend.controllers;


import ma.taghia.fmdc_backend.entities.PW;
import ma.taghia.fmdc_backend.services.PWService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pws")
@CrossOrigin(origins = "https://fmdc-frontend.vercel.app")
public class PWController {
    @Autowired
    private PWService pwService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody PW pwDetails) {
        PW pw = pwService.create(pwDetails);
        if (pw == null) {
            return new ResponseEntity<>("Invalid request Data", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(pw, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        PW pw = pwService.findById(id);
        if (pw == null) {
            return new ResponseEntity<>("PW not found", HttpStatus.NOT_FOUND);
        } else {
            boolean deleted = pwService.delete(pw);
            if (deleted) {
                return new ResponseEntity<>("PW deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("PW associated to a user", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody PW pw) {
        if (pwService.findById(id) == null) {
            return new ResponseEntity<>("PW not found", HttpStatus.NOT_FOUND);
        } else {
            pw.setId(id);
            return new ResponseEntity<>(pwService.update(pw), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<PW>> findAll() {
        return new ResponseEntity<>(pwService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        PW pw = pwService.findById(id);
        if (pw == null) {
            return new ResponseEntity<>("PW not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(pw, HttpStatus.OK);
        }
    }
}

