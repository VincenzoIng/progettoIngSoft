package com.client.support;

import lombok.Data;

import java.util.List;

@Data
public class RichiestaAggiuntaEsame {

    private EsameSupp esameSupp;
    private List<String> risposte;
    private List<String> domande;
}
