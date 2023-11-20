package com.lipcam.ClinicaApiSpring.dto.agendas;

import com.lipcam.ClinicaApiSpring.entities.Agendas;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendasDTO {
    Long IdAgenda;
    LocalDateTime Data;
    Integer IdStatus;
    String Status;
    Long IdMedico;
    String Medico;
    Long IdProcedimento;
    String Precedimento;
    LocalDateTime DataAgendamento;

    public AgendasDTO(Agendas entity) {
        IdAgenda = entity.getID_AGENDA_LONG();
        Data = entity.getDATA_DTI();
        IdStatus = entity.getStatusAgenda().getID_STATUS_INT();
        Status = entity.getStatusAgenda().getDESCRICAO_STR();
        IdMedico = entity.getMedicos().getID_MEDICO_LONG();
        Medico = entity.getMedicos().getNOME_STR();
        IdProcedimento = entity.getProcedimentos().getID_PROCEDIMENTO_LONG();
        Precedimento = entity.getProcedimentos().getDESCRICAO_STR();
        DataAgendamento = entity.getDATA_AGENDAMENTO_DTI();
    }
}
