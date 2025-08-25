package com.navida.telecom_gestao.controller;

import com.navida.telecom_gestao.model.Fatura;
import com.navida.telecom_gestao.service.FaturaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/faturas")
public class FaturaController {

    private final FaturaService faturaService;

    public FaturaController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

    @GetMapping
    @PreAuthorize("hasRole('GERENTE')")
    public List<Fatura> listar() {
        return faturaService.listarTodasFaturas();
    }
}
