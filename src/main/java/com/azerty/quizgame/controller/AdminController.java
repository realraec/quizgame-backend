package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.AdminDTO;
import com.azerty.quizgame.model.dto.CountsDTO;
import com.azerty.quizgame.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admins")
@CrossOrigin("*")
public class AdminController {

    private final AdminService adminService;


    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        try {
            List<AdminDTO> admins = adminService.getAllAdmins();
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        try {
            AdminDTO admin = adminService.getAdminById(id);
            if (admin != null) {
                return new ResponseEntity<>(admin, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<AdminDTO> saveAdmin(@RequestBody AdminDTO admin) {
        try {
            return new ResponseEntity<>(adminService.saveAdmin(admin), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AdminDTO> updateAdminById(@RequestBody AdminDTO admin, @PathVariable Long id) {
        try {
            AdminDTO updatedAdmin = adminService.updateAdminById(admin, id);
            if (updatedAdmin != null) {
                return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteAdminById(@PathVariable Long id) {
        try {
            boolean deletedOrNot = adminService.deleteAdminById(id);
            if (deletedOrNot) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/counts")
    public ResponseEntity<CountsDTO> getInternCountAndQuizCount() {
        try {
            CountsDTO counts = adminService.getInternCountAndQuizCount();
            return new ResponseEntity<>(counts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
