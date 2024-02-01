package com.fabiocarlesso.recargaexpresstyrant.recarga.repository;

import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Recarga;
import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecargaRepository extends JpaRepository<Recarga, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Recarga r SET r.status = :status WHERE r.id = :id")
    void atualizaRecargaStatus(Long id, Status status);
}
