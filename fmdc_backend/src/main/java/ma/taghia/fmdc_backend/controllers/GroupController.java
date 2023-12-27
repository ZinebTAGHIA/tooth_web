package ma.taghia.fmdc_backend.controllers;


import ma.taghia.fmdc_backend.entities.Group;
import ma.taghia.fmdc_backend.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Group groupDetails) {
        Group group = groupService.create(groupDetails);
        if (group == null) {
            return new ResponseEntity<>("Invalid request Data", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(group, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Group group = groupService.findById(id);
        if (group == null) {
            return new ResponseEntity<>("Group not found", HttpStatus.NOT_FOUND);
        } else {
            boolean deleted = groupService.delete(group);
            if (deleted) {
                return new ResponseEntity<>("Group deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Group associated to a user", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Group group) {
        if (groupService.findById(id) == null) {
            return new ResponseEntity<>("Group not found", HttpStatus.NOT_FOUND);
        } else {
            group.setId(id);
            return new ResponseEntity<>(groupService.update(group), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Group>> findAll() {
        return new ResponseEntity<>(groupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        Group group = groupService.findById(id);
        if (group == null) {
            return new ResponseEntity<>("Group not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(group, HttpStatus.OK);
        }
    }
}

