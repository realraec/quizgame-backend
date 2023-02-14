package com.azerty.quizgame.dao;

import com.azerty.quizgame.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminDAO extends CrudRepository<Admin, Long> {

//    @Query("SELECT r FROM Rendezvous r WHERE r.medecin.id = (:id)")
//    List<Rendezvous> getAllRendezvousByMedecinId(Long id);

}
