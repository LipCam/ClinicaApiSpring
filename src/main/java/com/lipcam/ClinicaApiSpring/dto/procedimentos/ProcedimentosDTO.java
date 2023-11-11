package com.lipcam.ClinicaApiSpring.dto.procedimentos;

import com.lipcam.ClinicaApiSpring.entities.Procedimentos;
import lombok.Data;

@Data
public class ProcedimentosDTO {
     Long IdProcedimento;
     String Descricao;
     Double Valor;

    public ProcedimentosDTO(Procedimentos entity) {
        IdProcedimento = entity.getID_PROCEDIMENTO_LONG();
        Descricao = entity.getDESCRICAO_STR();
        Valor = entity.getVALOR_DEC();
    }
}
