package ma.taghia.fmdc_backend.controllers;


import ma.taghia.fmdc_backend.entities.Professor;
import ma.taghia.fmdc_backend.services.ProfessorService;
import ma.taghia.fmdc_backend.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/professors")
@CrossOrigin(origins = "https://fmdc-frontend.vercel.app")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String UPLOAD_DIRECTORY = "uploads";

    @PostMapping("")
    public ResponseEntity<Object> create(@RequestBody Professor professorDetails) {
        String hashedPassword = passwordEncoder.encode(professorDetails.getPassword());
        professorDetails.setPassword(hashedPassword);
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
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Professor updatedProfessor) {
        Professor existingProfessor = professorService.findById(id);

        if (existingProfessor == null) {
            return new ResponseEntity<>("Professor not found", HttpStatus.NOT_FOUND);
        }

        mergeProfessorAttributes(existingProfessor, updatedProfessor);

        Professor updated = professorService.update(existingProfessor);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PutMapping("/image/{id}")
    public ResponseEntity<Object> updateProfessorImage(
            @PathVariable int id,
            @RequestParam("image") MultipartFile imageFile
    ) {
        Professor existingProfessor = professorService.findById(id);

        if (existingProfessor == null) {
            return new ResponseEntity<>("Professor not found", HttpStatus.NOT_FOUND);
        }
        deleteExistingPhoto(existingProfessor.getImage());

        try {
            File directory = new File(UPLOAD_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = id + "_" + imageFile.getOriginalFilename();
            String filePath = UPLOAD_DIRECTORY + File.separator + fileName;

            byte[] bytes = imageFile.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            existingProfessor.setImage(filePath);
            professorService.update(existingProfessor);

            return new ResponseEntity<>("Image uploaded and URL updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/professor/{id}/image")
    public ResponseEntity<byte[]> getProfessorImage(@PathVariable int id) {
        Professor existingProfessor = professorService.findById(id);

        if (existingProfessor == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Path imagePath = Paths.get(existingProfessor.getImage());
            byte[] imageData = Files.readAllBytes(imagePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.setPragma("no-cache");
            headers.setExpires(0);

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("")
//    @PreAuthorize("isAuthenticated()")
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

    private void mergeProfessorAttributes(Professor existingProfessor, Professor updatedProfessor) {
        if (updatedProfessor.getFirstName() != null) {
            existingProfessor.setFirstName(updatedProfessor.getFirstName());
        }
        if (updatedProfessor.getLastName() != null) {
            existingProfessor.setLastName(updatedProfessor.getLastName());
        }
        if (updatedProfessor.getGrade() != null) {
            existingProfessor.setGrade(updatedProfessor.getGrade());
        }
        if (updatedProfessor.getUserName() != null) {
            existingProfessor.setUserName(updatedProfessor.getUserName());
        }
        if (updatedProfessor.getPassword() != null) {
            existingProfessor.setPassword(passwordEncoder.encode(updatedProfessor.getPassword()));
        }
    }

    private void deleteExistingPhoto(String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            File existingFile = new File(imageUrl);
            if (existingFile.exists()) {
                existingFile.delete();
            }
        }
    }
}

