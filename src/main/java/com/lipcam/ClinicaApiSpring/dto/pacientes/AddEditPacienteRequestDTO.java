package com.lipcam.ClinicaApiSpring.dto.pacientes;

import lombok.Data;

@Data
public class AddEditPacienteRequestDTO {
   String Nome;
   String CPF;
   String Celular;
}
