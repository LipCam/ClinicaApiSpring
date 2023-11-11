package com.lipcam.ClinicaApiSpring.services;

import com.lipcam.ClinicaApiSpring.dto.ResponseDTO;
import com.lipcam.ClinicaApiSpring.dto.medicos.AddEditMedicoRequestDTO;
import com.lipcam.ClinicaApiSpring.dto.medicos.MedicosDTO;
import com.lipcam.ClinicaApiSpring.entities.Medicos;
import com.lipcam.ClinicaApiSpring.repositories.MedicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicosService {

    @Autowired
    private MedicosRepository _repository;

    @Transactional(readOnly = true)
    public List<MedicosDTO> findAll() {
        return _repository.findAll().stream().map(x -> new MedicosDTO(x)).toList();
    }

    public MedicosDTO findById(Long Id) {
        Medicos entity = _repository.findById(Id).orElse(null);
        if (entity != null)
            return new MedicosDTO(entity);
        return null;
    }

    public MedicosDTO add(AddEditMedicoRequestDTO addEditMedicoRequestDTO) {
        Medicos entity = _repository.save(new Medicos(addEditMedicoRequestDTO.getNome(), addEditMedicoRequestDTO.getCPF(), addEditMedicoRequestDTO.getNumRegistro()));
        return new MedicosDTO(entity);
    }

    public ResponseDTO edit(Long Id, AddEditMedicoRequestDTO addEditMedicoRequestDTO) {
        Medicos entity = _repository.findById(Id).orElse(null);

        if(entity != null){
            entity.setNOME_STR(addEditMedicoRequestDTO.getNome());
            entity.setCPF_STR(addEditMedicoRequestDTO.getCPF());
            entity.setNUM_REGISTRO_STR(addEditMedicoRequestDTO.getNumRegistro());
            _repository.save(entity);
            return new ResponseDTO("OK", "Edição realizada com sucesso");
        }
        return new ResponseDTO("Erro", "Registro inexistente");
    }

    public ResponseDTO delete(Long Id) {
        Medicos entity = _repository.findById(Id).orElse(null);

        if(entity != null) {
            _repository.delete(entity);
            return new ResponseDTO("OK", "Exclusão realizada com sucesso");
        }
        return new ResponseDTO("Erro", "Registro inexistente");
    }
}
