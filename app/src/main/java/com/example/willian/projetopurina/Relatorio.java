package com.example.willian.projetopurina;

/**
 * Created by willian on 29/10/17.
 */

public class Relatorio {

    /*RELATÓRIO*/
    private int id;

    private String data;
    private String peso;
    private String pressao_emagrecimento;
    private String peso_perdido;
    private String kcal_perdidas_mes;
    private String kcal_perdidas_dia;
    private String necessidade_de_kcal_dia;
    private String kcal_fornecida_dia;
    private String taxa_metabolica;
    public Relatorio(){}

    public String getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getPressao_emagrecimento() {
        return pressao_emagrecimento;
    }

    public void setPressao_emagrecimento(String pressao_emagrecimento) {
        this.pressao_emagrecimento = pressao_emagrecimento;
    }

    public String getPeso_perdido() {
        return peso_perdido;
    }

    public void setPeso_perdido(String peso_perdido) {
        this.peso_perdido = peso_perdido;
    }

    public String getKcal_perdidas_mes() {
        return kcal_perdidas_mes;
    }

    public void setKcal_perdidas_mes(String kcal_perdidas_mes) {
        this.kcal_perdidas_mes = kcal_perdidas_mes;
    }

    public String getKcal_perdidas_dia() {
        return kcal_perdidas_dia;
    }

    public void setKcal_perdidas_dia(String kcal_perdidas_dia) {
        this.kcal_perdidas_dia = kcal_perdidas_dia;
    }

    public String getNecessidade_de_kcal_dia() {
        return necessidade_de_kcal_dia;
    }

    public void setNecessidade_de_kcal_dia(String necessidade_de_kcal_dia) {
        this.necessidade_de_kcal_dia = necessidade_de_kcal_dia;
    }

    public String getKcal_fornecida_dia() {
        return kcal_fornecida_dia;
    }

    public void setKcal_fornecida_dia(String kcal_fornecida_dia) {
        this.kcal_fornecida_dia = kcal_fornecida_dia;
    }

    public String getTaxa_metabolica() {
        return taxa_metabolica;
    }

    public void setTaxa_metabolica(String taxa_metabolica) {
        this.taxa_metabolica = taxa_metabolica;
    }
}
