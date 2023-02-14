package com.azerty.quizgame.service;

import com.azerty.quizgame.model.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> getAllAdmins() throws Exception;

    Admin getAdminById(Long id) throws Exception;

    Admin saveAdmin(Admin admin) throws Exception;

    boolean deleteAdminById(Long id) throws Exception;

    Admin updateAdminById(Admin admin, Long id) throws Exception;

}
