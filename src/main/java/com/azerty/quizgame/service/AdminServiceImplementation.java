package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AdminDAO;
import com.azerty.quizgame.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = (List<Admin>) adminDAO.findAll();

        if (!admins.isEmpty()) {
            return admins;
        } else {
            return null;
        }
    }

    @Override
    public Admin getAdminById(Long id) {
        Optional<Admin> admin = adminDAO.findById(id);
        return admin.orElse(null);
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminDAO.save(admin);
    }

    @Override
    public boolean deleteAdminById(Long id) {
        Optional<Admin> checkAdmin = adminDAO.findById(id);
        if (checkAdmin.isPresent()) {
            adminDAO.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Admin updateAdminById(Admin admin, Long id) {
        Optional<Admin> checkAdmin = adminDAO.findById(id);
        if (checkAdmin.isPresent()) {
            admin.setId(id);
            return adminDAO.save(admin);
        } else {
            return null;
        }
    }

}
