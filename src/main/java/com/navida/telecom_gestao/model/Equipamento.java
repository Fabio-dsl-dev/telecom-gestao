package com.navida.telecom_gestao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipamento {

    @Id
    private String numeroDeSerie;

    private String modelo;

    private String status;

    @OneToOne
    @JoinColumn(name = "contrato_id")
    private Contrato contrato;

}
