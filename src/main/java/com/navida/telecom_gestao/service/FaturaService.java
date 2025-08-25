package com.navida.telecom_gestao.service;

import com.navida.telecom_gestao.model.Contrato;
import com.navida.telecom_gestao.model.Fatura;
import com.navida.telecom_gestao.model.Servico;
import com.navida.telecom_gestao.repository.ContratoRepository;
import com.navida.telecom_gestao.repository.FaturaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FaturaService {

    private final ContratoRepository contratoRepository;
    private final FaturaRepository faturaRepository;

    public FaturaService(ContratoRepository contratoRepository, FaturaRepository faturaRepository) {
        this.contratoRepository = contratoRepository;
        this.faturaRepository = faturaRepository;
    }

    @Scheduled(cron = "0 0 0 1 * *") // todo dia 1º do mês à meia-noite
    public void gerarFaturasMensais() {
        List<Contrato> contratos = contratoRepository.findByStatus("ATIVO");
        for (Contrato c : contratos) {
            BigDecimal total = BigDecimal.ZERO;
            if (c.getServicos() != null) {
                for (Servico s : c.getServicos()) {
                    if (s.isAtivo() && s.getValorMensal() != null) {
                        total = total.add(s.getValorMensal());
                    }
                }
            }
            Fatura f = new Fatura();
            f.setContrato(c);
            f.setValorTotal(total);
            f.setDataVencimento(LocalDate.now().withDayOfMonth(1).plusMonths(1));
            f.setStatus("PENDENTE");
            faturaRepository.save(f);
        }
    }

    public List<Fatura> listarTodasFaturas() {
        return faturaRepository.findAll();
    }
}
