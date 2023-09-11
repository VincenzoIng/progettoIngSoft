package com.clientstud.support;

import com.clientstud.pattern.BuilderIF;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RisultatiSupp {

    private int id;
    private String nomeesame;
    private String nomeprofessore;
    private String dataesame;
    private String matricola;
    private int punteggio;

    public static class Builder implements BuilderIF{
        private int id;
        private String nomeesame;
        private String nomeprofessore;
        private String dataesame;
        private String matricola;
        private int punteggio;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder nomeesame(String nomeesame){
            this.nomeesame = nomeesame;
            return this;
        }

        public Builder nomeprofessore(String nomeprofessore){
            this.nomeprofessore = nomeprofessore;
            return this;
        }

        public Builder dataesame(String dataesame){
            this.dataesame = dataesame;
            return this;
        }

        public Builder matricola(String matricola){
            this.matricola = matricola;
            return this;
        }

        public Builder punteggio(int punteggio){
            this.punteggio = punteggio;
            return this;
        }

        @Override
        public RisultatiSupp build() {
            RisultatiSupp risultatiSupp = new RisultatiSupp();
            risultatiSupp.id = this.id;
            risultatiSupp.nomeprofessore = this.nomeprofessore;
            risultatiSupp.nomeesame = this.nomeesame;
            risultatiSupp.dataesame = this.dataesame;
            risultatiSupp.matricola = this.matricola;
            risultatiSupp.punteggio = this.punteggio;
            return risultatiSupp;
        }


    }
}
