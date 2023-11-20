package com.lipcam.ClinicaApiSpring.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "AGENDAS_TAB")
public class Agendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID_AGENDA_LONG;

    LocalDateTime DATA_DTI;

    // 0-Livre, 1-Agendado, 2-Em atendimento, 3-Atendido
    @ManyToOne
    @JoinColumn(name = "ID_STATUS_INT", referencedColumnName = "ID_STATUS_INT")
    StatusAgenda StatusAgenda;
//    Integer ID_STATUS_INT;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PACIENTE_LONG", referencedColumnName = "ID_PACIENTE_LONG")
    Pacientes Pacientes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MEDICO_LONG", referencedColumnName = "ID_MEDICO_LONG")
    Medicos Medicos;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PROCEDIMENTO_LONG", referencedColumnName = "ID_PROCEDIMENTO_LONG")
    Procedimentos Procedimentos;

    LocalDateTime DATA_AGENDAMENTO_DTI;

    public Agendas() {
    }
}
