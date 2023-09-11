package com.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class EsameDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String professore;
    private String materia;
    private String data;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> risposte;
}
