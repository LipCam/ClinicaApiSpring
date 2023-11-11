package com.lipcam.ClinicaApiSpring.controllers;

import com.lipcam.ClinicaApiSpring.dto.ResponseDTO;
import com.lipcam.ClinicaApiSpring.dto.pacientes.AddEditPacienteRequestDTO;
import com.lipcam.ClinicaApiSpring.dto.pacientes.PacientesDTO;
import com.lipcam.ClinicaApiSpring.services.PacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pacientes")
public class PacientesController {

    @Autowired
    PacientesService _service;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(_service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        PacientesDTO dto = _service.findById(id);
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Registro inexistente"));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody AddEditPacienteRequestDTO addEditRequestDTO) {
        if (addEditRequestDTO.getNome().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo Nome deve ser preenchido"));

        if (addEditRequestDTO.getCPF().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo CPF deve ser preenchido"));

        PacientesDTO dto = _service.add(addEditRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody AddEditPacienteRequestDTO addEditRequestDTO) {
        if (addEditRequestDTO.getNome().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo Nome deve ser preenchido"));

        if (addEditRequestDTO.getCPF().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo CPF deve ser preenchido"));

        ResponseDTO responseDTO = _service.edit(id, addEditRequestDTO);

        if (responseDTO.getResult().equals("OK"))
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseDTO responseDTO = _service.delete(id);

        if (responseDTO.getResult().equals("OK"))
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
}
