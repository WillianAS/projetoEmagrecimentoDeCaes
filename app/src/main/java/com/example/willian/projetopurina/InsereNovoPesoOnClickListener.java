package com.example.willian.projetopurina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by willian on 05/11/17.
 */

public class InsereNovoPesoOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {

        final Context context = view.getContext();

        final Activity activity = (Activity) context;

        int idCachorro = activity.getIntent().getExtras().getInt("idCachorro");

        Relatorio recuperaPressaoEmagrecimento = new BancoController(context).recuperarRelatorio(idCachorro);

        float pressaoEmagrecimento = Float.parseFloat(recuperaPressaoEmagrecimento.getPressao_emagrecimento());

        final EditText txtNovoPeso = activity.findViewById(R.id.txtNovoPeso);
        float peso = Float.parseFloat(txtNovoPeso.getText().toString());

        /*FÓRMULAS*/
//        float pesoPerdido = peso * pressaoEmagrecimento;
//        float kcalMes = pesoPerdido * 9000;
//        float kcalDia = kcalMes / 30;

//        float termoExp = (float) Math.pow((peso - pesoPerdido), 0.75);
//        float necessKcal = termoExp * 100;

//        float kcalFornecida = necessKcal - kcalDia;
//        float taxaMetabolica = termoExp * 70;

        String stringPeso = Float.toString(peso);
        String stringPressaoEmagrecimento = Float.toString(pressaoEmagrecimento);
//        String stringPesoPerdido = Float.toString(pesoPerdido);
//        String stringKcalMes = Float.toString(kcalMes);
//        String stringKcalDia = Float.toString(kcalDia);
//        String stringNecessKcal = Float.toString(necessKcal);
//        String stringKcalFornecida = Float.toString(kcalFornecida);
//        String stringTaxaMetabolica = Float.toString(taxaMetabolica);

        /*SET DAS NOVAS INFORMAÇÕES*/
        Relatorio relatorio = new Relatorio();

//        String monthname = (String) android.text.format.DateFormat.format("MMMM", new Date());

        relatorio.setId(idCachorro);
//        relatorio.setData(monthname);
        relatorio.setPeso(stringPeso);
        relatorio.setPressao_emagrecimento(stringPressaoEmagrecimento);
//        relatorio.setPeso_perdido(stringPesoPerdido);
//        relatorio.setKcal_perdidas_mes(stringKcalMes);
//        relatorio.setKcal_perdidas_dia(stringKcalDia);
//        relatorio.setNecessidade_de_kcal_dia(stringNecessKcal);
//        relatorio.setKcal_fornecida_dia(stringKcalFornecida);
//        relatorio.setTaxa_metabolica(stringTaxaMetabolica);

//        Toast.makeText(context, "ID "+idCachorro+"MES" +monthname+ "PESO"+stringPeso+ "PE"+stringPressaoEmagrecimento +
//                "PESO PERDIDO"+stringPesoPerdido+ "KCAL MES"+stringKcalMes+ "KCAL DIA"+stringKcalDia
//                +"NCESS KCAL"+stringNecessKcal+ "KCAL FORNECIDA"+stringKcalFornecida+
//                "TAXA MET"+stringTaxaMetabolica, Toast.LENGTH_SHORT).show();


//        boolean novaInsercao = new BancoController(context).createRelatorio(relatorio);

        Toast.makeText(context, "ID "+idCachorro+" NOVO PESO "+ pesoPerdido +" PE "+ pressaoEmagrecimento, Toast.LENGTH_SHORT).show();
//        if (novaInsercao) {
//        } else {
//            Toast.makeText(context, "Algo errado!", Toast.LENGTH_SHORT).show();
//        }


    }
}
