package com.lipcam.ClinicaApiSpring.dto.pacientes;

import com.lipcam.ClinicaApiSpring.entities.Pacientes;
import lombok.Data;

@Data
public class PacientesDTO {
     Long IdPaciente;
     String Nome;
     String CPF;
     String Celular;

    public PacientesDTO(Pacientes entity) {
        IdPaciente = entity.getID_PACIENTE_LONG();
        Nome = entity.getNOME_STR();
        CPF = entity.getCPF_STR();
        Celular = entity.getCELULAR_STR();
    }
}
