package com.navida.telecom_gestao.service;

import com.navida.telecom_gestao.model.Equipamento;
import com.navida.telecom_gestao.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    private final EquipamentoRepository equipamentoRepository;

    public EstoqueService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public Equipamento salvarEquipamento(Equipamento e) {
        return equipamentoRepository.save(e);
    }

    public List<Equipamento> listarTodosEquipamentos() {
        return equipamentoRepository.findAll();
    }

    public Equipamento buscarPorNumeroDeSerie(String ns) {
        return equipamentoRepository.findById(ns).orElse(null);
    }

    public Equipamento atualizarStatus(String ns, String status) {
        return equipamentoRepository.findById(ns).map(e -> {
            e.setStatus(status);
            return equipamentoRepository.save(e);
        }).orElse(null);
    }
}
