package com.br.sunioweb.editais.repository;

import com.br.sunioweb.editais.model.Requisition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisitionRepository extends JpaRepository<Requisition,Long> {
}
