package com.lipcam.ClinicaApiSpring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "STATUS_AGENDA_TAB")
public class StatusAgenda {
    @Id
    Integer ID_STATUS_INT;
    String DESCRICAO_STR;

    public StatusAgenda() {
    }

    public StatusAgenda(Integer ID_STATUS_INT) {
        this.ID_STATUS_INT = ID_STATUS_INT;
    }
}
