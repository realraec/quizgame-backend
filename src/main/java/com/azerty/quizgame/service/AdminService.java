package com.azerty.quizgame.service;

import com.azerty.quizgame.model.dto.AdminDTO;

import java.util.List;

public interface AdminService {

    List<AdminDTO> getAllAdmins() throws Exception;

    AdminDTO getAdminById(Long id) throws Exception;

    AdminDTO saveAdmin(AdminDTO admin) throws Exception;

    boolean deleteAdminById(Long id) throws Exception;

    AdminDTO updateAdminById(AdminDTO admin, Long id) throws Exception;

}
