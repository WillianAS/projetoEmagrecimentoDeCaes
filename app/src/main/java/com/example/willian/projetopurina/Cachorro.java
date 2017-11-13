package com.example.willian.projetopurina;

/**
 * Created by willian on 29/10/17.
 */

public class Cachorro {

    /*CACHORRO*/
    private int id;
    private String cpf_pessoa;
    private String nome_animal;
    private int idade;
    private String sexo;
    private String raca;
    private String pesoIdeal;

    public Cachorro(){}

    public String getNome_animal() {
        return nome_animal;
    }

    public void setNome_animal(String nome_animal) {
        this.nome_animal = nome_animal;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getCpf_pessoa() {
        return cpf_pessoa;
    }

    public void setCpf_pessoa(String cpf_pessoa) {
        this.cpf_pessoa = cpf_pessoa;
    }

    public String getPesoIdeal() {
        return pesoIdeal;
    }

    public void setPesoIdeal(String pesoIdeal) {
        this.pesoIdeal = pesoIdeal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
