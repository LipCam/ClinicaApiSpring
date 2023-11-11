package com.lipcam.ClinicaApiSpring.dto.medicos;

import com.lipcam.ClinicaApiSpring.entities.Medicos;
import lombok.Data;

@Data
public class MedicosDTO {
    Long IdMedico;
    String Nome;
    String NumRegistro;

    public MedicosDTO(Medicos entity) {
        IdMedico = entity.getID_MEDICO_LONG();
        Nome = entity.getNOME_STR();
        NumRegistro = entity.getNUM_REGISTRO_STR();
    }
}
