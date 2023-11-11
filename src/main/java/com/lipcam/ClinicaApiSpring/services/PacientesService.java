package com.lipcam.ClinicaApiSpring.services;

import com.lipcam.ClinicaApiSpring.dto.ResponseDTO;
import com.lipcam.ClinicaApiSpring.dto.pacientes.AddEditPacienteRequestDTO;
import com.lipcam.ClinicaApiSpring.dto.pacientes.PacientesDTO;
import com.lipcam.ClinicaApiSpring.entities.Pacientes;
import com.lipcam.ClinicaApiSpring.repositories.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientesService {

    @Autowired
    PacientesRepository _repository;

    public List<PacientesDTO> findAll() {
        return _repository.findAll().stream().map(x -> new PacientesDTO(x)).toList();
    }

    public PacientesDTO findById(Long Id) {
        Pacientes entity = _repository.findById(Id).orElse(null);
        if (entity != null)
            return new PacientesDTO(entity);
        return null;
    }

    public PacientesDTO add(AddEditPacienteRequestDTO addEditRequestDTO) {
        Pacientes entity = _repository.save(new Pacientes(addEditRequestDTO.getNome(), addEditRequestDTO.getCPF(), addEditRequestDTO.getCelular()));
        return new PacientesDTO(entity);
    }


    public ResponseDTO edit(Long id, AddEditPacienteRequestDTO addEditRequestDTO) {
        Pacientes entity = _repository.findById(id).orElse(null);
        if (entity != null) {
            entity.setNOME_STR(addEditRequestDTO.getNome());
            entity.setCPF_STR(addEditRequestDTO.getCPF());
            entity.setCELULAR_STR(addEditRequestDTO.getCelular());
            _repository.save(entity);
            return new ResponseDTO("OK", "Edição realizada com sucesso");
        }

        return new ResponseDTO("Erro", "Registro inexistente");
    }

    public  ResponseDTO delete(Long id)
    {
        Pacientes entity = _repository.findById(id).orElse(null);
        if (entity != null) {
            _repository.delete(entity);
            return new ResponseDTO("OK", "Exclusão realizada com sucesso");
        }

        return new ResponseDTO("Erro", "Registro inexistente");
    }
}
