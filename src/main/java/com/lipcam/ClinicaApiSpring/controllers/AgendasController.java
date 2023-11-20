package com.lipcam.ClinicaApiSpring.controllers;

import com.lipcam.ClinicaApiSpring.dto.ResponseDTO;
import com.lipcam.ClinicaApiSpring.dto.agendas.RealizarAgdRequestDTO;
import com.lipcam.ClinicaApiSpring.services.AgendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/agendas")
public class AgendasController {

    @Autowired
    AgendasService _service;

    @GetMapping(value = "/paciente/{idPaciente}")
    public ResponseEntity findByPacientes(@PathVariable Long idPaciente) {
        return ResponseEntity.status(HttpStatus.OK).body(_service.findByPacientes(idPaciente));
    }

    @GetMapping(value = "/disponiveis")
    public ResponseEntity findByDisponiveis(@RequestParam String dataInicio, @RequestParam String horaInicio,
                                            @RequestParam String dataFim, @RequestParam String horaFim,
                                            @RequestParam Long idProcedimento, @RequestParam Long idMedico) {
        return ResponseEntity.status(HttpStatus.OK).body(_service.findByDisponiveis(dataInicio, horaInicio, dataFim, horaFim, idProcedimento, idMedico));
    }

    @PostMapping
    public  ResponseEntity realizarAgendamento(@RequestBody RealizarAgdRequestDTO realizarAgdRequestDTO) {
        if (realizarAgdRequestDTO.getIdAgenda() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo IdAgenda deve ser preenchido"));

        if (realizarAgdRequestDTO.getIdPaciente() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo IdPaciente deve ser preenchido"));

        return ResponseEntity.status(HttpStatus.CREATED).body(_service.realizarAgendamento(realizarAgdRequestDTO));
    }

    @DeleteMapping(value = "/{idAgenda}")
    public ResponseEntity cancelarAgendamento(@PathVariable Long idAgenda){
        if (idAgenda == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo IdAgenda deve ser preenchido"));

        return ResponseEntity.status(HttpStatus.OK).body(_service.cancelarAgendamento(idAgenda));
    }
}
