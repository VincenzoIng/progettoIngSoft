package com.client.support;

import com.client.pattern.BuilderIF;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EsameResponseSupp {
    private int id;
    private String professore;
    private String materia;
    private String data;

    public static class Builder implements BuilderIF{
        private int id;
        private String professore;
        private String materia;
        private String data;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder professore(String professore){
            this.professore = professore;
            return this;
        }

        public Builder materia(String materia){
            this.materia = materia;
            return this;
        }

        public Builder data(String data){
            this.data = data;
            return this;
        }

        @Override
        public EsameResponseSupp build() {
            EsameResponseSupp esameResponseSupp = new EsameResponseSupp();
            esameResponseSupp.id = this.id;
            esameResponseSupp.professore = this.professore;
            esameResponseSupp.materia = this.materia;
            esameResponseSupp.data = this.data;
            return esameResponseSupp;
        }


    }
}
