package com.server.repository;

import com.server.entity.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudenteDBRepository extends JpaRepository<Studente, String> {

    Studente findStudenteByMatricola(String matricola);


}
