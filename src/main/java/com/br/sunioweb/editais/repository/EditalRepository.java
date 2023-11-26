package com.br.sunioweb.editais.repository;

import com.br.sunioweb.editais.model.Edital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditalRepository extends JpaRepository<Edital,Long> {
    List<Edital> findByVisibility(boolean visibility);
}
