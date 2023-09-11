package com.clientstud.support;

import com.clientstud.pattern.BuilderIF;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrenotazioniDBSupp {

    private int id;
    private int idesame;
    private String materiaesame;
    private String cf;
    private String matricola;

    public static class Builder implements BuilderIF{
        private int id;
        private int idesame;
        private String materiaesame;
        private String cf;
        private String matricola;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder idesame(int idesame){
            this.idesame = idesame;
            return this;
        }

        public Builder materiaesame(String materiaesame){
            this.materiaesame = materiaesame;
            return this;
        }

        public Builder cf(String cf){
            this.cf = cf;
            return this;
        }

        public Builder matricola(String matricola){
            this.matricola = matricola;
            return this;
        }

        @Override
        public PrenotazioniDBSupp build() {
            PrenotazioniDBSupp prenotazioniDBSupp = new PrenotazioniDBSupp();
            prenotazioniDBSupp.id = this.id;
            prenotazioniDBSupp.idesame = this.idesame;
            prenotazioniDBSupp.materiaesame = this.materiaesame;
            prenotazioniDBSupp.cf = this.cf;
            prenotazioniDBSupp.matricola = this.matricola;
            return prenotazioniDBSupp;
        }
    }

}
