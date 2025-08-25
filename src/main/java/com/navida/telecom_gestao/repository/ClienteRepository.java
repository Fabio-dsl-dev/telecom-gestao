package com.navida.telecom_gestao.repository;

import com.navida.telecom_gestao.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
