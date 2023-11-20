package com.lipcam.ClinicaApiSpring.repositories;

import com.lipcam.ClinicaApiSpring.entities.Agendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendasRepository extends JpaRepository<Agendas, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM AGENDAS_TAB WHERE ID_PACIENTE_LONG = :idPaciente ORDER BY DATA_DTI DESC")
    List<Agendas> findByPacientes(Long idPaciente);

    @Query(nativeQuery = true,
            value = "SELECT AGD.*,\n" +
                    "\t  MED.NOME_STR AS MEDICO_STR,\n" +
                    "\t  PRC.DESCRICAO_STR AS PROCEDIMENTO_STR\n" +
                    "FROM AGENDAS_TAB AGD\n" +
                    "INNER JOIN MEDICOS_TAB MED ON MED.ID_MEDICO_LONG = AGD.ID_MEDICO_LONG\n" +
                    "INNER JOIN PROCEDIMENTOS_TAB PRC ON PRC.ID_PROCEDIMENTO_LONG = AGD.ID_PROCEDIMENTO_LONG\n" +
                    "WHERE AGD.ID_STATUS_INT = 0 AND AGD.DATA_DTI BETWEEN :dataInicio AND :dataFim " +
                    "   AND (:idProcedimento = 0 OR AGD.ID_PROCEDIMENTO_LONG = :idProcedimento)\n" +
                    "   AND (:idMedico = 0 OR AGD.ID_MEDICO_LONG = :idMedico)")
    List<Agendas> findByDisponiveis(LocalDateTime dataInicio, LocalDateTime dataFim, Long idProcedimento, Long idMedico);

    /*@Query(nativeQuery = true,
            value = "SELECT AGD.ID_AGENDA_LONG,\n" +
                    "\t  AGD.DATA_DTI,\n" +
                    "\t  AGD.ID_MEDICO_LONG,\n" +
                    "\t  MED.NOME_STR AS MEDICO_STR,\n" +
                    "\t  AGD.ID_PROCEDIMENTO_LONG,\n" +
                    "\t  PRC.DESCRICAO_STR AS PROCEDIMENTO_STR\n" +
                    "FROM AGENDAS_TAB AGD\n" +
                    "INNER JOIN MEDICOS_TAB MED ON MED.ID_MEDICO_LONG = AGD.ID_MEDICO_LONG\n" +
                    "INNER JOIN PROCEDIMENTOS_TAB PRC ON PRC.ID_PROCEDIMENTO_LONG = AGD.ID_PROCEDIMENTO_LONG\n" +
                    "WHERE AGD.ID_STATUS_INT = 0 AND AGD.DATA_DTI BETWEEN :dataInicio AND :dataFim " +
                    "   AND (:idProcedimento = 0 OR AGD.ID_PROCEDIMENTO_LONG = :idProcedimento)\n" +
                    "   AND (:idMedico = 0 OR AGD.ID_MEDICO_LONG = :idMedico)")*/
}
