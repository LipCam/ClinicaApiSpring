package com.lipcam.ClinicaApiSpring.repositories;

import com.lipcam.ClinicaApiSpring.entities.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientesRepository extends JpaRepository<Pacientes, Long> {
}
