package com.example.willian.projetopurina;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;


public class InsereNovoPesoOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {

        final Context context = view.getContext();

        final Activity activity = (Activity) context;

        int idCachorro = activity.getIntent().getExtras().getInt("idCachorro");

        Relatorio recuperaDadoAnterior = new BancoController(context).recuperarRelatorio(idCachorro);

        float pressaoEmagrecimento = (Float.parseFloat(recuperaDadoAnterior.getPressao_emagrecimento())) * 1000;
        float pesoAnterior = Float.parseFloat(recuperaDadoAnterior.getPeso());
        float pesoPerdidoAnterior = Float.parseFloat(recuperaDadoAnterior.getPeso_perdido());

        /*PEGA O NOVO PESO INSERIDO NO EDIT TEXT A ACTIVITY*/
        final EditText txtNovoPeso = activity.findViewById(R.id.txtNovoPeso);
        float peso = Float.parseFloat(txtNovoPeso.getText().toString());

        /*VERIFICAR SE OS MESES 1~2 OU 3~4 PARA ALTERAR PRESSAO DE EMAGRECIMENTO*/
        int contador = new BancoController(context).contadorDeMes(idCachorro);

        /*VERIFICA SE O MES ESTA ENTRE 3~4 E AUMENTA A PRESSAO DE EMAGRECIMENTO EM 0.5%*/
        if (contador == 2) {
            pressaoEmagrecimento += 5;
        }
        else if (contador == 4) {
            pressaoEmagrecimento += 5;
        }

        /*FÓRMULAS*/

        float pesoPerdidoRealmente = pesoAnterior - peso;
        float porcentPesoPerdido = (pesoPerdidoRealmente / pesoPerdidoAnterior) * 100;

        if (porcentPesoPerdido < 70) {
            Toast.makeText(context, "Porcentagem de emagrecimento menor que 70%", Toast.LENGTH_SHORT).show();
            pressaoEmagrecimento += 10;
        }
        else if (porcentPesoPerdido >= 70 && porcentPesoPerdido < 90) {
            Toast.makeText(context, "Porcentagem de emagrecimento entre 70% e 90%", Toast.LENGTH_SHORT).show();
            pressaoEmagrecimento += 5;
        }
        else if (porcentPesoPerdido >= 90 && porcentPesoPerdido < 110) {
            Toast.makeText(context, "Objetivo de perda atingido com sucesso!"+pesoAnterior, Toast.LENGTH_SHORT).show();
        }
        else if (porcentPesoPerdido >= 110 && porcentPesoPerdido < 120) {
            Toast.makeText(context, "Porcentagem de emagrecimento entre 110% e 120%", Toast.LENGTH_SHORT).show();
            pressaoEmagrecimento -= 5;
        }
        else if (porcentPesoPerdido >= 120) {
            pressaoEmagrecimento -= 10;
            Toast.makeText(context, "Porcentagem de emagrecimento maior que 120%", Toast.LENGTH_SHORT).show();
        }

        float realPressaoEmagrecimento = (pressaoEmagrecimento / 1000);

        float pesoPerdido = peso * realPressaoEmagrecimento;
        float kcalMes = pesoPerdido * 9000;
        float kcalDia = kcalMes / 30;

        float termoExp = (float) Math.pow((peso - pesoPerdido), 0.75);
        float necessKcal = termoExp * 100;

        float kcalFornecida = necessKcal - kcalDia;
        float taxaMetabolica = termoExp * 70;
        float quantidadeRacao = (kcalFornecida/2990)*1000;

        String stringPeso = Float.toString(peso);
        String stringPressaoEmagrecimento = Float.toString(realPressaoEmagrecimento);
        String stringPesoPerdido = Float.toString(pesoPerdido);
        String stringKcalMes = Float.toString(kcalMes);
        String stringKcalDia = Float.toString(kcalDia);
        String stringNecessKcal = Float.toString(necessKcal);
        String stringKcalFornecida = Float.toString(kcalFornecida);
        String stringTaxaMetabolica = Float.toString(taxaMetabolica);
        String stringQuantidadeRacao = Float.toString(quantidadeRacao);

        /*SET DAS NOVAS INFORMAÇÕES*/
        Relatorio relatorio = new Relatorio();

        String monthname = (String) android.text.format.DateFormat.format("MMMM", new Date());

        relatorio.setId(idCachorro);
        relatorio.setData(monthname);
        relatorio.setPeso(stringPeso);
        relatorio.setPressao_emagrecimento(stringPressaoEmagrecimento);
        relatorio.setPeso_perdido(stringPesoPerdido);
        relatorio.setKcal_perdidas_mes(stringKcalMes);
        relatorio.setKcal_perdidas_dia(stringKcalDia);
        relatorio.setNecessidade_de_kcal_dia(stringNecessKcal);
        relatorio.setKcal_fornecida_dia(stringKcalFornecida);
        relatorio.setTaxa_metabolica(stringTaxaMetabolica);
        relatorio.setQuantidade_racao(stringQuantidadeRacao);

        boolean novaInsercao = new BancoController(context).createRelatorio(relatorio);

        if (novaInsercao) {
            Toast.makeText(context, "SUCESSO!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "ALGO ERRADO!", Toast.LENGTH_SHORT).show();
        }
    }
}
