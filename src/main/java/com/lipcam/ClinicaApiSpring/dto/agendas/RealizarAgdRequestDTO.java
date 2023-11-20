package com.lipcam.ClinicaApiSpring.dto.agendas;

import lombok.Data;

@Data
public class RealizarAgdRequestDTO {
    Long idAgenda;
    Long idPaciente;
}
