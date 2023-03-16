package com.azerty.quizgame.service;

import com.azerty.quizgame.dao.AdminDAO;
import com.azerty.quizgame.model.dto.AdminDTO;
import com.azerty.quizgame.model.dto.CountsDTO;
import com.azerty.quizgame.model.entity.Admin;
import com.azerty.quizgame.utils.AdminMapper;
import com.azerty.quizgame.utils.CountsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {

    private final AdminDAO adminDAO;
    private final AdminMapper adminMapper = new AdminMapper();
    private final CountsMapper countsMapper = new CountsMapper();


    @Autowired
    public AdminServiceImplementation(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }


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
        return admin.map(adminMapper::toAdminDTO).orElse(null);
    }

    @Override
    public AdminDTO saveAdmin(AdminDTO admin) {
        return adminMapper.toAdminDTO(adminDAO.save(adminMapper.toAdmin(admin)));
    }

    @Override
    public AdminDTO updateAdminById(AdminDTO admin, Long id) {
        Optional<Admin> checkAdmin = adminDAO.findById(id);
        if (checkAdmin.isPresent()) {
            Admin adminAsEntity = adminMapper.toAdmin(admin);
            adminAsEntity.setId(id);
            return adminMapper.toAdminDTO(adminDAO.save(adminAsEntity));
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

    @Override
    public CountsDTO getInternCountAndQuizCount() {
        Long[] counts = adminDAO.findInternCountAndQuizCount();
        return countsMapper.toCountsDTO(counts);
    }

}
