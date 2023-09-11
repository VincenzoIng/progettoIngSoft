package com.server.repository;

import com.server.entity.Risultati;
import com.server.entity.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RisultatiDBRepository extends JpaRepository<Risultati, Integer> {

    List<Risultati> findAllByStudente(Studente s);
}
