package com.lipcam.ClinicaApiSpring.dto.medicos;

import lombok.Data;

@Data
public class AddEditMedicoRequestDTO {
   String Nome;
   String CPF;
   String NumRegistro;
}
