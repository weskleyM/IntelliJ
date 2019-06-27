package com.ufc.main.repositories;

import com.ufc.main.entities.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {

    public List<Prato> findByStatus(int status);
}
