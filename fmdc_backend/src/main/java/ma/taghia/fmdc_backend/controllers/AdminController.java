package ma.taghia.fmdc_backend.controllers;


import ma.taghia.fmdc_backend.entities.Admin;
import ma.taghia.fmdc_backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Admin adminDetails) {
        Admin admin = adminService.create(adminDetails);
        if (admin == null) {
            return new ResponseEntity<>("Invalid request Data", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Admin admin = adminService.findById(id);
        if (admin == null) {
            return new ResponseEntity<>("Admin not found", HttpStatus.NOT_FOUND);
        } else {
            boolean deleted = adminService.delete(admin);
            if (deleted) {
                return new ResponseEntity<>("Admin deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Admin associated to a user", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Admin admin) {
        if (adminService.findById(id) == null) {
            return new ResponseEntity<>("Admin not found", HttpStatus.NOT_FOUND);
        } else {
            admin.setId(id);
            return new ResponseEntity<>(adminService.update(admin), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Admin>> findAll() {
        return new ResponseEntity<>(adminService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Admin admin = adminService.findById(id);
        if (admin == null) {
            return new ResponseEntity<>("Admin not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        }
    }
}

