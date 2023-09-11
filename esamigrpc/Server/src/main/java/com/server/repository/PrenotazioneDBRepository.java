package com.server.repository;

import com.server.entity.EsameDB;
import com.server.entity.PrenotazioneDB;
import com.server.entity.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneDBRepository extends JpaRepository<PrenotazioneDB, Integer> {

    PrenotazioneDB findByStudenteAndEsame(Studente studente, EsameDB esameDB);

    List<PrenotazioneDB> findByStudente(Studente studente);

}
