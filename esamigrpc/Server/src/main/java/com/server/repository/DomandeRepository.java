package com.server.repository;

import com.server.entity.Domanda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomandeRepository extends JpaRepository<Domanda, Integer> {

    List<Domanda> findAllByIdesame(Integer id);

}
