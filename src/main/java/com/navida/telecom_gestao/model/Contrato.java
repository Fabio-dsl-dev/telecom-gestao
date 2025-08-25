package com.navida.telecom_gestao.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "contratos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "contrato_servico",
        joinColumns = @JoinColumn(name = "contrato_id"),
        inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private Set<Servico> servicos;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private String status;

    @OneToOne(mappedBy = "contrato", cascade = CascadeType.ALL)
    private Equipamento equipamento;

}
