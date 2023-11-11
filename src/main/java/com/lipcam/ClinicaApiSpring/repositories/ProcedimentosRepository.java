package com.lipcam.ClinicaApiSpring.repositories;

import com.lipcam.ClinicaApiSpring.entities.Procedimentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedimentosRepository extends JpaRepository<Procedimentos, Long> {
}
