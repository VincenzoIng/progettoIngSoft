package com.server.repository;


import com.server.entity.RisposteConsigliate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RisposteConsigliateRepository extends JpaRepository<RisposteConsigliate, Integer> {

    List<RisposteConsigliate> findAllByIdesame(Integer id);
}
