package com.navida.telecom_gestao.controller;

import com.navida.telecom_gestao.model.Equipamento;
import com.navida.telecom_gestao.service.EstoqueService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    @PreAuthorize("hasRole('TECNICO') or hasRole('GERENTE')")
    public List<Equipamento> listar() {
        return estoqueService.listarTodosEquipamentos();
    }

    @GetMapping("/{ns}")
    @PreAuthorize("hasRole('TECNICO') or hasRole('GERENTE')")
    public Equipamento buscar(@PathVariable("ns") String ns) {
        return estoqueService.buscarPorNumeroDeSerie(ns);
    }

    @PostMapping
    @PreAuthorize("hasRole('TECNICO') or hasRole('GERENTE')")
    public Equipamento criar(@RequestBody Equipamento equipamento) {
        return estoqueService.salvarEquipamento(equipamento);
    }

    @PutMapping("/{ns}/status")
    @PreAuthorize("hasRole('TECNICO') or hasRole('GERENTE')")
    public Equipamento atualizarStatus(@PathVariable("ns") String ns, @RequestBody String status) {
        return estoqueService.atualizarStatus(ns, status);
    }
}
