package com.navida.telecom_gestao.service;

import com.navida.telecom_gestao.model.Cliente;
import com.navida.telecom_gestao.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Long id, Cliente dados) {
        return clienteRepository.findById(id).map(c -> {
            c.setNome(dados.getNome());
            c.setCpf(dados.getCpf());
            c.setEmail(dados.getEmail());
            c.setTelefone(dados.getTelefone());
            return clienteRepository.save(c);
        }).orElseGet(() -> {
            dados.setId(id);
            return clienteRepository.save(dados);
        });
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
