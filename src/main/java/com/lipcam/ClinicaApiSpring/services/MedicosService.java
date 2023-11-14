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

    @Transactional(readOnly = true)
    public MedicosDTO findById(Long id) {
        Medicos entity = _repository.findById(id).orElse(null);
        if (entity != null)
            return new MedicosDTO(entity);
        return null;
    }

    @Transactional(readOnly = true)
    public MedicosDTO add(AddEditMedicoRequestDTO addEditRequestDTO) {
        Medicos entity = _repository.save(new Medicos(addEditRequestDTO.getNome(), addEditRequestDTO.getCPF(), addEditRequestDTO.getNumRegistro()));
        return new MedicosDTO(entity);
    }

    @Transactional(readOnly = true)
    public ResponseDTO edit(Long Id, AddEditMedicoRequestDTO addEditRequestDTO) {
        Medicos entity = _repository.findById(Id).orElse(null);

        if(entity != null){
            entity.setNOME_STR(addEditRequestDTO.getNome());
            entity.setCPF_STR(addEditRequestDTO.getCPF());
            entity.setNUM_REGISTRO_STR(addEditRequestDTO.getNumRegistro());
            _repository.save(entity);
            return new ResponseDTO("OK", "Edição realizada com sucesso");
        }
        return new ResponseDTO("Erro", "Registro inexistente");
    }

    @Transactional(readOnly = true)
    public ResponseDTO delete(Long id) {
        Medicos entity = _repository.findById(id).orElse(null);

        if(entity != null) {
            _repository.delete(entity);
            return new ResponseDTO("OK", "Exclusão realizada com sucesso");
        }
        return new ResponseDTO("Erro", "Registro inexistente");
    }
}
