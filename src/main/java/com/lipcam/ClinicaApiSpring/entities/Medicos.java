package com.lipcam.ClinicaApiSpring.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "MEDICOS_TAB")
public class Medicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID_MEDICO_LONG;
    String NOME_STR;
    String CPF_STR;
    String NUM_REGISTRO_STR;

    public Medicos() {
    }

    public Medicos(String NOME_STR, String CPF_STR, String NUM_REGISTRO_STR) {
        this.NOME_STR = NOME_STR;
        this.CPF_STR = CPF_STR;
        this.NUM_REGISTRO_STR = NUM_REGISTRO_STR;
    }
}
