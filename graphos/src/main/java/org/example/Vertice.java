package org.example;

public class Vertice{
        private String nome;
        private String bairro;

        public Vertice(String nome, String bairro) {
            this.nome = nome;
            this.bairro = bairro;
        }

        public String getNome() {
            return nome;
        }

        public String getBairro() {
            return bairro;
        }

        @Override
        public String toString() {
            return nome + " (" + bairro + ")";
        }
}
