package com.lipcam.ClinicaApiSpring.controllers;

import com.lipcam.ClinicaApiSpring.dto.ResponseDTO;
import com.lipcam.ClinicaApiSpring.dto.procedimentos.AddEditProcedimentoRequestDTO;
import com.lipcam.ClinicaApiSpring.dto.procedimentos.ProcedimentosDTO;
import com.lipcam.ClinicaApiSpring.services.ProcedimentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/procedimentos")
public class ProcedimentosController {

    @Autowired
    ProcedimentosService _service;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(_service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        ProcedimentosDTO dto = _service.findById(id);
        if (dto != null)
            return ResponseEntity.status(HttpStatus.OK).body(dto);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Registro inexistente"));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody AddEditProcedimentoRequestDTO addEditRequestDTO) {
        if (addEditRequestDTO.getDescricao().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo Descrição deve ser preenchido"));

        if (addEditRequestDTO.getValor().isNaN())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo Valor deve ser preenchido"));

        ProcedimentosDTO dto = _service.add(addEditRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody AddEditProcedimentoRequestDTO addEditRequestDTO) {
        if (addEditRequestDTO.getDescricao().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo Descrição deve ser preenchido"));

        if (addEditRequestDTO.getValor().isNaN())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo Valor deve ser preenchido"));

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
