package com.lipcam.ClinicaApiSpring.repositories;

import com.lipcam.ClinicaApiSpring.entities.Medicos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicosRepository extends JpaRepository<Medicos, Long> {
}
