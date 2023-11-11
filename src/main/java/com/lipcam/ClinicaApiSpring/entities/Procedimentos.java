package com.lipcam.ClinicaApiSpring.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PROCEDIMENTOS_TAB")
public class Procedimentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID_PROCEDIMENTO_LONG;

    @Column(length = 150)
    String DESCRICAO_STR;

    Double VALOR_DEC;

    public Procedimentos() {
    }

    public Procedimentos(String DESCRICAO_STR, Double VALOR_DEC) {
        this.DESCRICAO_STR = DESCRICAO_STR;
        this.VALOR_DEC = VALOR_DEC;
    }
}
