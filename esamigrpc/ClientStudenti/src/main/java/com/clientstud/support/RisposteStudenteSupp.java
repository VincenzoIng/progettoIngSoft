package com.clientstud.support;

import lombok.Data;

import java.util.List;

@Data
public class RisposteStudenteSupp {

    private int id;
    private String cf;
    private List<String> risposte;

}
