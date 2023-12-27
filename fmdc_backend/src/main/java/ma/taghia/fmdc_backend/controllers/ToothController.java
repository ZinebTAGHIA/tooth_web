package ma.taghia.fmdc_backend.controllers;


import ma.taghia.fmdc_backend.entities.Tooth;
import ma.taghia.fmdc_backend.services.ToothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teeth")
@CrossOrigin(origins = "http://localhost:3000")
public class ToothController {
    @Autowired
    private ToothService toothService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Tooth toothDetails) {
        Tooth tooth = toothService.create(toothDetails);
        if (tooth == null) {
            return new ResponseEntity<>("Invalid request Data", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(tooth, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Tooth tooth = toothService.findById(id);
        if (tooth == null) {
            return new ResponseEntity<>("Tooth not found", HttpStatus.NOT_FOUND);
        } else {
            boolean deleted = toothService.delete(tooth);
            if (deleted) {
                return new ResponseEntity<>("Tooth deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Tooth associated to a user", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Tooth tooth) {
        if (toothService.findById(id) == null) {
            return new ResponseEntity<>("Tooth not found", HttpStatus.NOT_FOUND);
        } else {
            tooth.setId(id);
            return new ResponseEntity<>(toothService.update(tooth), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Tooth>> findAll() {
        return new ResponseEntity<>(toothService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Tooth tooth = toothService.findById(id);
        if (tooth == null) {
            return new ResponseEntity<>("Tooth not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tooth, HttpStatus.OK);
        }
    }
}

