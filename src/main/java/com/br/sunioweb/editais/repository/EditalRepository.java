package com.br.sunioweb.editais.repository;



import com.br.sunioweb.editais.model.Edital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EditalRepository extends JpaRepository<Edital, Long>, JpaSpecificationExecutor<Edital> {
    Page<Edital> findByVisibility(boolean visibility, Pageable pageable);

    Page<Edital> findAll(Pageable pageable);



    // Consulta personalizada para buscar editais com visibility=true
    @Query( value = "SELECT * FROM edital " +
                    "WHERE edital.visibility = true " +
                    "AND (LOWER(edital.name) LIKE LOWER(CONCAT('%', :param, '%')) " +
                    "OR LOWER(edital.number) LIKE LOWER(CONCAT('%', :param, '%')))" +
                    "ORDER BY edital.date_publication desc",
            countQuery = "SELECT id FROM edital " +
                        "WHERE edital.visibility = true " +
                    "AND (LOWER(edital.name) LIKE LOWER(CONCAT('%', :param, '%')) " +
                    "OR LOWER(edital.number) LIKE LOWER(CONCAT('%', :param, '%')))",
            nativeQuery = true)
    Page<Edital> findVisibleEditaisByParam(@Param("param") String param, Pageable pageable );

    @Query( value = "SELECT * FROM edital " +
            "AND (LOWER(edital.name) LIKE LOWER(CONCAT('%', :param, '%')) " +
            "OR LOWER(edital.number) LIKE LOWER(CONCAT('%', :param, '%')))" +
            "ORDER BY edital.date_publication desc",
            countQuery = "SELECT id FROM edital " +
                    "WHERE edital.visibility = true " +
                    "AND (LOWER(edital.name) LIKE LOWER(CONCAT('%', :param, '%')) " +
                    "OR LOWER(edital.number) LIKE LOWER(CONCAT('%', :param, '%')))",
            nativeQuery = true)
    Page<Edital> findEditaisByParam(@Param("param") String param, Pageable pageable );


}




