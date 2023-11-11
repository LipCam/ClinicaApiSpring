package com.lipcam.ClinicaApiSpring.services;

import com.lipcam.ClinicaApiSpring.dto.ResponseDTO;
import com.lipcam.ClinicaApiSpring.dto.procedimentos.AddEditProcedimentoRequestDTO;
import com.lipcam.ClinicaApiSpring.dto.procedimentos.ProcedimentosDTO;
import com.lipcam.ClinicaApiSpring.entities.Procedimentos;
import com.lipcam.ClinicaApiSpring.repositories.ProcedimentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedimentosService {

    @Autowired
    ProcedimentosRepository _repository;

    public List<ProcedimentosDTO> findAll() {
        return _repository.findAll().stream().map(x -> new ProcedimentosDTO(x)).toList();
    }

    public ProcedimentosDTO findById(Long id) {
        Procedimentos entity = _repository.findById(id).orElse(null);
        if (entity != null)
            return new ProcedimentosDTO(entity);
        return null;
    }

    public ProcedimentosDTO add(AddEditProcedimentoRequestDTO addEditRequestDTO) {
        Procedimentos entity = _repository.save(new Procedimentos(addEditRequestDTO.getDescricao(), addEditRequestDTO.getValor()));
        return new ProcedimentosDTO(entity);
    }


    public ResponseDTO edit(Long id, AddEditProcedimentoRequestDTO addEditRequestDTO) {
        Procedimentos entity = _repository.findById(id).orElse(null);
        if (entity != null) {
            entity.setDESCRICAO_STR(addEditRequestDTO.getDescricao());
            entity.setVALOR_DEC(addEditRequestDTO.getValor());
            _repository.save(entity);
            return new ResponseDTO("OK", "Edição realizada com sucesso");
        }

        return new ResponseDTO("Erro", "Registro inexistente");
    }

    public  ResponseDTO delete(Long id)
    {
        Procedimentos entity = _repository.findById(id).orElse(null);
        if (entity != null) {
            _repository.delete(entity);
            return new ResponseDTO("OK", "Exclusão realizada com sucesso");
        }

        return new ResponseDTO("Erro", "Registro inexistente");
    }
}
