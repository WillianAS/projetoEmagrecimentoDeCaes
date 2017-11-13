package com.example.willian.projetopurina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

public class InformacoesDaPrimeiraInsercao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_da_primeira_insercao);

        String nomeTutor = getIntent().getExtras().getString("nomeTutor");
        TextView textNomeCachorro = (TextView) findViewById(R.id.getNome);
        textNomeCachorro.setText(nomeTutor);

        String cpf = getIntent().getExtras().getString("cpf");
        TextView txtCpf = (TextView) findViewById(R.id.getCpf);
        txtCpf.setText(cpf);

        String nomeAnimal = getIntent().getExtras().getString("nomeAnimal");
        TextView txtNomeAnimal = (TextView) findViewById(R.id.getNomeAnimal);
        txtNomeAnimal.setText(nomeAnimal);

        int idade = getIntent().getIntExtra("idade", 0);
        TextView txtIdade = (TextView) findViewById(R.id.getIdade);
        txtIdade.setText(idade + " ano(s)");

        float peso = getIntent().getFloatExtra("peso", 0);
        TextView txtPeso = (TextView) findViewById(R.id.getPeso);
        txtPeso.setText(peso + " kg");

        String dataInicio = DateUtils
                .from(new Date())
                .format();
        TextView txtDataInicio = (TextView) findViewById(R.id.getData);
        txtDataInicio.setText(dataInicio);


        String pressEmagre = getIntent().getExtras().getString("pressaoEmagrecimento");
        TextView txtPressEmagre = (TextView) findViewById(R.id.getPressaoDeEmagrecimento);
        txtPressEmagre.setText(pressEmagre+" %");

        String dietaRacao = getIntent().getExtras().getString("dietaRacao");
        TextView txtDietaRacao = (TextView) findViewById(R.id.getRacao);
        txtDietaRacao.setText(dietaRacao+" g");

        String kcalDia = getIntent().getExtras().getString("kcalDia");
        TextView txtKcalDia = (TextView) findViewById(R.id.getKcalDiarias);
        txtKcalDia.setText(kcalDia+" Kcal/dia");

        String pesoPerder = getIntent().getExtras().getString("pesoAPerder");
        TextView txtPesoPerder = (TextView) findViewById(R.id.getPerdaDePeso);
        txtPesoPerder.setText(pesoPerder+" kg");

        String pesoRetorno = getIntent().getExtras().getString("pesoRetorno");
        TextView txtPesoRetorno = (TextView) findViewById(R.id.getPesoFinalMes);
        txtPesoRetorno.setText(pesoRetorno+" kg");

        String dataRetorno = DateUtils
                .from(new Date())
                .addMonths(1)
                .format();
        TextView txtDataRetorno = (TextView) findViewById(R.id.getDataRetorno);
        txtDataRetorno.setText(dataRetorno);

        String pesoIdeal = getIntent().getExtras().getString("pesoIdeal");
        TextView txtPesoIdeal = (TextView) findViewById(R.id.getPesoIdeal);
        txtPesoIdeal.setText(pesoIdeal+" kg");

        String previsaoFim = getIntent().getExtras().getString("previsaoFim");
        TextView txtPrevisaoFiml = (TextView) findViewById(R.id.getPrevisaoFim);
        txtPrevisaoFiml.setText(previsaoFim+" mes(es)");


        Button btnConcluir = (Button) findViewById(R.id.btnOK);
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Context context = view.getContext();
                final Activity activity = (Activity) context;

                Intent intent = new Intent(activity, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}