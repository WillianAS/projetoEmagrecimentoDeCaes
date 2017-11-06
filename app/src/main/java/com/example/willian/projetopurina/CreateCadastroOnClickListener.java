package com.example.willian.projetopurina;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.DecimalFormat;
import android.icu.util.Calendar;
import android.provider.MediaStore;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.time.Month;
import java.util.Date;
import java.util.Locale;

import static android.R.attr.cacheColorHint;
import static android.R.attr.checked;
import static android.R.attr.fingerprintAuthDrawable;
import static android.R.attr.flipInterval;
import static android.R.attr.publicKey;
import static android.R.attr.switchMinWidth;

/**
 * Created by willian on 11/10/17.
 */

public class CreateCadastroOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        /*TRANSFORMANDO O CONTEXTO EM ACTIVITY*/
        final Activity a = (Activity) context;

        /*RECUPERANDO E INSERINDO INFORMAÇÕES REFERENTES A PESSOA*/
        final EditText editTextNomeCliente = a.findViewById(R.id.nomeCliente);
        final EditText editTextCpfCliente = a.findViewById(R.id.cpfCliente);

        String cpf = editTextCpfCliente.getText().toString();
        String nome_dono = editTextNomeCliente.getText().toString();

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(cpf);
        pessoa.setNome_dono(nome_dono);

        /*RECUPERANDO E INSERINDO INFORMAÇÕES REFERENTES AO CACHORRO*/
        final EditText editTextNomeCachorro = a.findViewById(R.id.nomeCao);
        final EditText editTextIdade = a.findViewById(R.id.idadeCao);
        final EditText editTextRaca = a.findViewById(R.id.raca);

        String nome_cachorro = editTextNomeCachorro.getText().toString();
        int idade = Integer.parseInt(editTextIdade.getText().toString());
        String raca = editTextRaca.getText().toString();

        final Cachorro cachorro = new Cachorro();
        cachorro.setNome_animal(nome_cachorro);
        cachorro.setIdade(idade);
        cachorro.setRaca(raca);

        /*RECUPERANDO VALOR DE ACORDO COM SCORE*/
        final RadioButton rbScore7 = a.findViewById(R.id.score7);
        final RadioButton rbScore8 = a.findViewById(R.id.score8);
        final RadioButton rbScore9 = a.findViewById(R.id.score9);
        float score;

        if (rbScore7.isChecked()) {
            score = 0.15f;
            String stringScore = Float.toString(score);
            cachorro.setScore(stringScore);
        }
        else if (rbScore8.isChecked()) {
            score = 0.25f;
            String stringScore = Float.toString(score);
            cachorro.setScore(stringScore);
        }
        else if (rbScore9.isChecked()){
            score = 0.35f;
            String stringScore = Float.toString(score);
            cachorro.setScore(stringScore);
        }

        final Relatorio relatorio = new Relatorio();
        float pressaoEmagrecimento;

        final RadioButton rbFemea = a.findViewById(R.id.femea);

        if (rbFemea.isChecked()) {
            cachorro.setSexo("fêmea");
            pressaoEmagrecimento = 0.06f;
        } else {
            cachorro.setSexo("macho");
            pressaoEmagrecimento = 0.05f;
        }

        if (idade > 10) {
            pressaoEmagrecimento -= 0.01f;
        }
        else if (idade >= 6) {
            pressaoEmagrecimento -= 0.005f;
        }

        cachorro.setCpf_pessoa(cpf);

        /*RECUPERANDO E INSERINDO INFORMAÇÕES REFERENTES AO RELATÓRIO*/

        EditText editTextPeso = a.findViewById(R.id.pesoCao);

        /*IDENTIFICA O MES DO APARELHO PARA INSERIR NO BANCO*/
        String monthname = (String) android.text.format.DateFormat.format("MMMM", new Date());
        float peso = Float.parseFloat(editTextPeso.getText().toString());

        if (peso > 40) {
            pressaoEmagrecimento = pressaoEmagrecimento - 0.01f;
        }
        else if (peso >= 10) {
            pressaoEmagrecimento -= 0.005f;
        }

        RadioButton rbDoencaHepatica = a.findViewById(R.id.simEpaticaRenalCardica);

        if (rbDoencaHepatica.isChecked()) {
            pressaoEmagrecimento -= 0.005f;
        }

        RadioButton rbDisplasia = a.findViewById(R.id.simDisplasia);

        if (rbDisplasia.isChecked()) {
            pressaoEmagrecimento += 0.005f;
        }

        RadioButton rbLesaoColuna = a.findViewById(R.id.simLesaoColuna);

        if (rbLesaoColuna.isChecked()) {
            pressaoEmagrecimento += 0.01f;
        }

        RadioButton rbMetabolica = a.findViewById(R.id.simMetabolica);

        if (rbMetabolica.isChecked()) {
            Toast.makeText(context, "Verificar exames para melhor acompanhamento.", Toast.LENGTH_SHORT).show();
        }

        RadioButton rbAtividade = a.findViewById(R.id.muitoAtivo);

        if (rbAtividade.isChecked()) {
            pressaoEmagrecimento -= 0.005f;
        }

        /*FÓRMULAS*/
        float pesoPerdido = peso * pressaoEmagrecimento;
        float kcalMes = pesoPerdido * 9000;
        float kcalDia = kcalMes / 30;

        float termoExp = (float) Math.pow((peso - pesoPerdido), 0.75);
        float necessKcal = termoExp * 100;

        float kcalFornecida = necessKcal - kcalDia;
        float taxaMetabolica = termoExp * 70;

        String stringPeso = Float.toString(peso);
        String stringPressaoEmagrecimento = Float.toString(pressaoEmagrecimento);
        String stringPesoPerdido = Float.toString(pesoPerdido);
        String stringKcalMes = Float.toString(kcalMes);
        String stringKcalDia = Float.toString(kcalDia);
        String stringNecessKcal = Float.toString(necessKcal);
        String stringKcalFornecida = Float.toString(kcalFornecida);
        String stringTaxaMetabolica = Float.toString(taxaMetabolica);

        boolean pessoaSucesso = new BancoController(context).createPessoa(pessoa);
        int cachorroSucesso = new BancoController(context).createCachorro(cachorro);

        /*SERAO INSERIDOS EM RELATORIO*/
        relatorio.setId(cachorroSucesso);
        relatorio.setData(monthname);
        relatorio.setPeso(stringPeso);
        relatorio.setPressao_emagrecimento(stringPressaoEmagrecimento);
        relatorio.setPeso_perdido(stringPesoPerdido);
        relatorio.setKcal_perdidas_mes(stringKcalMes);
        relatorio.setKcal_perdidas_dia(stringKcalDia);
        relatorio.setNecessidade_de_kcal_dia(stringNecessKcal);
        relatorio.setKcal_fornecida_dia(stringKcalFornecida);
        relatorio.setTaxa_metabolica(stringTaxaMetabolica);

        boolean relatorioSucesso = new BancoController(context).createRelatorio(relatorio);

        if(pessoaSucesso && relatorioSucesso && cachorroSucesso != -1) {
            Toast.makeText(context, "INSERIDO COM SUCESSO!!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "DEU RUIM!!", Toast.LENGTH_SHORT).show();
        }
    }
}
