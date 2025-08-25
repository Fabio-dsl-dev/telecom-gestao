package com.navida.telecom_gestao.service;

import com.navida.telecom_gestao.model.Servico;
import com.navida.telecom_gestao.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public Servico salvarServico(Servico servico) {
        return servicoRepository.save(servico);
    }

    public List<Servico> listarTodosServicos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    public Servico atualizarServico(Long id, Servico dados) {
        return servicoRepository.findById(id).map(s -> {
            s.setNome(dados.getNome());
            s.setDescricao(dados.getDescricao());
            s.setValorMensal(dados.getValorMensal());
            s.setAtivo(dados.isAtivo());
            return servicoRepository.save(s);
        }).orElseGet(() -> {
            dados.setId(id);
            return servicoRepository.save(dados);
        });
    }
}
