package com.lipcam.ClinicaApiSpring.dto.agendas;

import com.lipcam.ClinicaApiSpring.entities.Agendas;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendasDispDTO {
    Long IdAgenda;
    LocalDateTime Data;
    Long IdMedico;
    String Medico;
    Long IdProcedimento;
    String Precedimento;

    public AgendasDispDTO(Agendas entity) {
        IdAgenda = entity.getID_AGENDA_LONG();
        Data = entity.getDATA_DTI();
        IdMedico = entity.getMedicos().getID_MEDICO_LONG();
        Medico = entity.getMedicos().getNOME_STR();
        IdProcedimento = entity.getProcedimentos().getID_PROCEDIMENTO_LONG();
        Precedimento = entity.getProcedimentos().getDESCRICAO_STR();
    }
}
