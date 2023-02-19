package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AdminDAO;
import com.azerty.quizgame.dto.AdminDTO;
import com.azerty.quizgame.model.Admin;
import com.azerty.quizgame.utils.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    private final AdminMapper adminMapper = new AdminMapper();


    @Override
    public List<AdminDTO> getAllAdmins() {
        Iterator<Admin> adminIterator = adminDAO.findAll().iterator();
        List<AdminDTO> admins = new ArrayList<>();
        while (adminIterator.hasNext()) {
            admins.add(adminMapper.toAdminDTO(adminIterator.next()));
        }

        if (!admins.isEmpty()) {
            return admins;
        } else {
            return null;
        }
    }

    @Override
    public AdminDTO getAdminById(Long id) {
        Optional<Admin> admin = adminDAO.findById(id);
        if (admin.isPresent()) {
            return adminMapper.toAdminDTO(admin.get());
        } else {
            return null;
        }
    }

    @Override
    public AdminDTO saveAdmin(AdminDTO admin) {
        return adminMapper.toAdminDTO(adminDAO.save(adminMapper.toAdmin(admin)));
    }

    @Override
    public AdminDTO updateAdminById(AdminDTO admin, Long id) {
        Optional<Admin> checkAdmin = adminDAO.findById(id);
        if (checkAdmin.isPresent()) {
            Admin adminAsModel = adminMapper.toAdmin(admin);
            adminAsModel.setId(id);
            return adminMapper.toAdminDTO(adminDAO.save(adminAsModel));
        } else {
            return null;
        }
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

}
