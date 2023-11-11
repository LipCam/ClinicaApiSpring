package com.lipcam.ClinicaApiSpring.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PACIENTES_TAB")
public class Pacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID_PACIENTE_LONG;

    @Column(length = 150)
    String NOME_STR;

    @Column(length = 15)
    String CPF_STR;

    @Column(length = 20)
    String CELULAR_STR;

    public Pacientes() {
    }

    public Pacientes(String NOME_STR, String CPF_STR, String CELULAR_STR) {
        this.NOME_STR = NOME_STR;
        this.CPF_STR = CPF_STR;
        this.CELULAR_STR = CELULAR_STR;
    }
}
