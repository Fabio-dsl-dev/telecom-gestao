package com.navida.telecom_gestao.controller;

import com.navida.telecom_gestao.model.Contrato;
import com.navida.telecom_gestao.service.ContratoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @PostMapping
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('GERENTE')")
    public Contrato criar(@RequestParam Long clienteId, @RequestBody List<Long> servicoIds) {
        return contratoService.criarContrato(clienteId, servicoIds);
    }

    @GetMapping
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('GERENTE')")
    public List<Contrato> listar() {
        return contratoService.listarTodosContratos();
    }
}
