package com.navida.telecom_gestao.controller;

import com.navida.telecom_gestao.model.Cliente;
import com.navida.telecom_gestao.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('GERENTE')")
    public List<Cliente> listar() {
        return clienteService.listarTodosClientes();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('GERENTE')")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {
        return clienteService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('GERENTE')")
    public Cliente criar(@RequestBody Cliente cliente) {
        return clienteService.salvarCliente(cliente);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('GERENTE')")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
