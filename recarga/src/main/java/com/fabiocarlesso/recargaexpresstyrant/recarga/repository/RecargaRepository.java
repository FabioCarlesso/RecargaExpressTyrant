package com.fabiocarlesso.recargaexpresstyrant.recarga.repository;

import com.fabiocarlesso.recargaexpresstyrant.recarga.model.Recarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecargaRepository extends JpaRepository<Recarga, Long> {
}
