package com.navida.telecom_gestao.repository;

import com.navida.telecom_gestao.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByStatus(String status);
}
