package com.br.sunioweb.editais.repository;

import com.br.sunioweb.editais.model.Requester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequesterRepository extends JpaRepository<Requester, Long> {

    List<Requester> findByNomenclature(String nomenclature);

    @Query(value = "SELECT * FROM requester r WHERE r.nomenclature = :nomenclature AND r.email = :email", nativeQuery = true)
    Requester findByNomenclatureAndEmail(@Param("nomenclature") String nomenclature, @Param("email") String email);

}
