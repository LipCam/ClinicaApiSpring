package com.lipcam.ClinicaApiSpring.services;

import com.lipcam.ClinicaApiSpring.dto.ResponseDTO;
import com.lipcam.ClinicaApiSpring.dto.agendas.AgendasDTO;
import com.lipcam.ClinicaApiSpring.dto.agendas.AgendasDispDTO;
import com.lipcam.ClinicaApiSpring.dto.agendas.RealizarAgdRequestDTO;
import com.lipcam.ClinicaApiSpring.entities.Agendas;
import com.lipcam.ClinicaApiSpring.entities.Pacientes;
import com.lipcam.ClinicaApiSpring.entities.StatusAgenda;
import com.lipcam.ClinicaApiSpring.repositories.AgendasRepository;
import com.lipcam.ClinicaApiSpring.repositories.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AgendasService {

    @Autowired
    AgendasRepository _repository;

    @Autowired
    PacientesRepository _pacientesRepository;

    @Transactional(readOnly = true)
    public List<AgendasDTO> findByPacientes(Long idPaciente) {
        return _repository.findByPacientes(idPaciente).stream().map(x -> new AgendasDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<AgendasDispDTO> findByDisponiveis(String dataInicio, String horaInicio, String dataFim, String horaFim, Long idProcedimento, Long idMedico) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");
        LocalDateTime dtInicio = LocalDateTime.parse(dataInicio + " " + horaInicio, formatter);
        LocalDateTime dtFim = LocalDateTime.parse(dataFim + " " + horaFim, formatter);

        return _repository.findByDisponiveis(dtInicio, dtFim, idProcedimento, idMedico).stream().map(x -> new AgendasDispDTO(x)).toList();
    }

    @Transactional
    public ResponseDTO realizarAgendamento(RealizarAgdRequestDTO realizarAgdRequestDTO)
    {
        Agendas agendas = _repository.findById(realizarAgdRequestDTO.getIdAgenda()).orElse(null);
        if(agendas == null)
            return new ResponseDTO("Erro", "Agendamento inexistente");

        Pacientes pacientes = _pacientesRepository.findById(realizarAgdRequestDTO.getIdPaciente()).orElse(null);
        if(pacientes == null)
            return new ResponseDTO("Erro", "Paciente inexistente");

        if(agendas.getPacientes() != null)
            return new ResponseDTO("Erro", "Agendamento já realizado por outro paciente");

        agendas.setPacientes(pacientes);
        agendas.setStatusAgenda(new StatusAgenda(1));
        agendas.setDATA_AGENDAMENTO_DTI(LocalDateTime.now());
        _repository.save(agendas);

        return new ResponseDTO("Ok", "Agendamento realizado com sucesso!");
    }

    @Transactional
    public ResponseDTO cancelarAgendamento(Long idAgenda) {
        Agendas agendas = _repository.findById(idAgenda).orElse(null);
        if(agendas == null)
            return new ResponseDTO("Erro", "Agendamento inexistente");

        agendas.setPacientes(null);
        agendas.setStatusAgenda(new StatusAgenda(0));
        agendas.setDATA_AGENDAMENTO_DTI(null);
        _repository.save(agendas);

        return new ResponseDTO("Ok", "Agendamento cancelado com sucesso!");
    }
}
