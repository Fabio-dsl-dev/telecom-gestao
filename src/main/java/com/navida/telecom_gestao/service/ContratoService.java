package com.navida.telecom_gestao.service;

import com.navida.telecom_gestao.model.Contrato;
import com.navida.telecom_gestao.model.Cliente;
import com.navida.telecom_gestao.model.Servico;
import com.navida.telecom_gestao.repository.ContratoRepository;
import com.navida.telecom_gestao.repository.ClienteRepository;
import com.navida.telecom_gestao.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;

    public ContratoService(ContratoRepository contratoRepository, ClienteRepository clienteRepository, ServicoRepository servicoRepository) {
        this.contratoRepository = contratoRepository;
        this.clienteRepository = clienteRepository;
        this.servicoRepository = servicoRepository;
    }

    public Contrato criarContrato(Long clienteId, List<Long> servicoIds) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Set<Servico> servicos = new HashSet<>();
        for (Long id : servicoIds) {
            Servico s = servicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado: " + id));
            servicos.add(s);
        }
        Contrato contrato = new Contrato();
        contrato.setCliente(cliente);
        contrato.setServicos(servicos);
        contrato.setStatus("ATIVO");
        contrato.setDataInicio(java.time.LocalDate.now());
        return contratoRepository.save(contrato);
    }

    public List<Contrato> listarTodosContratos() {
        return contratoRepository.findAll();
    }
}
