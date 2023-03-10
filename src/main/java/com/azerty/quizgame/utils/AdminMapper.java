package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.AdminDTO;
import com.azerty.quizgame.model.entity.Admin;
import com.azerty.quizgame.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDTO toAdminDTO(Admin admin) {
        Long id = admin.getId();
        String username = admin.getUsername();
        String password = admin.getPassword();
        String lastname = admin.getLastname();
        String firstname = admin.getFirstname();
        String email = admin.getEmail();
        Role role = admin.getRole();

        return new AdminDTO(id, username, password, lastname, firstname, email, role);
    }

    public Admin toAdmin(AdminDTO adminDTO) {
        return new Admin(adminDTO.getId(), adminDTO.getUsername(), adminDTO.getPassword(), adminDTO.getLastname(), adminDTO.getFirstname(), adminDTO.getEmail(), adminDTO.getRole());
    }

}
