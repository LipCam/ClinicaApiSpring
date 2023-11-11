package com.lipcam.ClinicaApiSpring.controllers;

import com.lipcam.ClinicaApiSpring.dto.ResponseDTO;
import com.lipcam.ClinicaApiSpring.dto.medicos.AddEditMedicoRequestDTO;
import com.lipcam.ClinicaApiSpring.dto.medicos.MedicosDTO;
import com.lipcam.ClinicaApiSpring.services.MedicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/medicos")
public class MedicosContrller {

    @Autowired
    MedicosService _service;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(_service.findAll());
    }

    @GetMapping(value = "/{Id}")
    public ResponseEntity findById(@PathVariable Long Id) {
        MedicosDTO medicosDTO = _service.findById(Id);
        if (medicosDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(medicosDTO);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Registro não encontrado"));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody AddEditMedicoRequestDTO addEditMedicoRequestDTO) {
        if (addEditMedicoRequestDTO.getNome().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo Nome deve ser preenchido"));

        if (addEditMedicoRequestDTO.getCPF().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo CPF deve ser preenchido"));

        if (addEditMedicoRequestDTO.getNumRegistro().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo Número Registro deve ser preenchido"));

        MedicosDTO medicosDTO = _service.add(addEditMedicoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(medicosDTO);
    }

    @PutMapping(value = "/{Id}")
    public ResponseEntity edit(@PathVariable Long Id, @RequestBody AddEditMedicoRequestDTO addEditMedicoRequestDTO) {
        if (addEditMedicoRequestDTO.getNome().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo Nome deve ser preenchido"));

        if (addEditMedicoRequestDTO.getCPF().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo CPF deve ser preenchido"));

        if (addEditMedicoRequestDTO.getNumRegistro().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("Erro", "Campo Número Registro deve ser preenchido"));

        System.out.println(Id);
        ResponseDTO responseDTO = _service.edit(Id, addEditMedicoRequestDTO);

        if (responseDTO.getResult().equals("OK"))
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }

    @DeleteMapping(value = "/{Id}")
    public ResponseEntity delete(@PathVariable Long Id) {
        ResponseDTO responseDTO = _service.delete(Id);

        if (responseDTO.getResult().equals("OK"))
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
}
