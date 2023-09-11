package com.server.repository;

import com.server.entity.EsameDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsameDBRepository extends JpaRepository<EsameDB, Integer> {
}
