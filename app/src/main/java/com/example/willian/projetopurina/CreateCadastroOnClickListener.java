package com.example.willian.projetopurina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import java.util.Date;

/**
 * Created by willian on 11/10/17.
 */

public class CreateCadastroOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        /*TRANSFORMANDO O CONTEXTO EM ACTIVITY*/
        final Activity a = (Activity) context;
        Intent intent = new Intent(a, InformacoesDaPrimeiraInsercao.class);

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
        float score = 0;

        if (rbScore7.isChecked()) {
            score = 0.15f;
        }
        else if (rbScore8.isChecked()) {
            score = 0.25f;
        }
        else if (rbScore9.isChecked()){
            score = 0.35f;
        }

        final Relatorio relatorio = new Relatorio();
        float pressaoEmagrecimento;

        final RadioButton rbFemea = a.findViewById(R.id.femea);

        if (rbFemea.isChecked()) {
            cachorro.setSexo("fêmea");
            pressaoEmagrecimento = 60;
        } else {
            cachorro.setSexo("macho");
            pressaoEmagrecimento = 50;
        }

        if (idade > 10) {
            pressaoEmagrecimento -= 10;
        }
        else if (idade >= 6) {
            pressaoEmagrecimento -= 5;
        }

        cachorro.setCpf_pessoa(cpf);

        /*RECUPERANDO E INSERINDO INFORMAÇÕES REFERENTES AO RELATÓRIO*/

        EditText editTextPeso = a.findViewById(R.id.pesoCao);

        /*IDENTIFICA O MES DO APARELHO PARA INSERIR NO BANCO*/
        String monthname = (String) android.text.format.DateFormat.format("MMMM", new Date());
        float peso = Float.parseFloat(editTextPeso.getText().toString());

        if (peso > 40) {
            pressaoEmagrecimento -= 10;
        }
        else if (peso >= 10) {
            pressaoEmagrecimento -= 5;
        }

        RadioButton rbDoencaHepatica = a.findViewById(R.id.simEpaticaRenalCardica);

        if (rbDoencaHepatica.isChecked()) {
            pressaoEmagrecimento -= 5;
        }

        RadioButton rbDisplasia = a.findViewById(R.id.simDisplasia);

        if (rbDisplasia.isChecked()) {
            pressaoEmagrecimento += 5;
        }

        RadioButton rbLesaoColuna = a.findViewById(R.id.simLesaoColuna);

        if (rbLesaoColuna.isChecked()) {
            pressaoEmagrecimento += 10;
        }

        RadioButton rbMetabolica = a.findViewById(R.id.simMetabolica);

        if (rbMetabolica.isChecked()) {
            Toast.makeText(context, "Verificar exames para melhor acompanhamento.", Toast.LENGTH_SHORT).show();
        }

        RadioButton rbAtividade = a.findViewById(R.id.muitoAtivo);

        if (rbAtividade.isChecked()) {
            pressaoEmagrecimento -= 5;
        }

        /*POR SER A PRIMEIRA INSERÇÃO A VARIAVEL PRESSAO DE EMAGRECIMENTO NOS DOIS PRIMEIROS MESES
         *É 1% A MENOS DO QUE O CALCULADO -------------------------------------------------------*/
        pressaoEmagrecimento -= 10;

        float totalPressaoEmagrecimento = pressaoEmagrecimento/1000;

        /*FÓRMULAS*/
        float pesoIdeal = peso - (peso * score);

        String stringPesoIdeal = Float.toString(pesoIdeal);
        cachorro.setPesoIdeal(stringPesoIdeal);


        float pesoPerdido = peso * totalPressaoEmagrecimento;
        float kcalMes = pesoPerdido * 9000;
        float kcalDia = kcalMes / 30;

        float termoExp = (float) Math.pow((peso - pesoPerdido), 0.75);
        float necessKcal = termoExp * 100;

        float kcalFornecida = necessKcal - kcalDia;
        float taxaMetabolica = termoExp * 70;
        float quantidadeRacao = (kcalFornecida/2990)*1000;

        String stringPeso = Float.toString(peso);
        String stringPressaoEmagrecimento = Float.toString(totalPressaoEmagrecimento);
        String stringPesoPerdido = Float.toString(pesoPerdido);
        String stringKcalMes = Float.toString(kcalMes);
        String stringKcalDia = Float.toString(kcalDia);
        String stringNecessKcal = Float.toString(necessKcal);
        String stringKcalFornecida = Float.toString(kcalFornecida);
        String stringTaxaMetabolica = Float.toString(taxaMetabolica);
        String stringQuantidadeRacao = Float.toString(quantidadeRacao);
//
//        boolean pessoaSucesso = new BancoController(context).createPessoa(pessoa);
//        int cachorroSucesso = new BancoController(context).createCachorro(cachorro);

        /*SERAO INSERIDOS EM RELATORIO*/
//        relatorio.setId(cachorroSucesso);
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

//        boolean relatorioSucesso = new BancoController(context).createRelatorio(relatorio);
//
//        if(pessoaSucesso && relatorioSucesso && cachorroSucesso != -1) {
//            Toast.makeText(context, "INSERIDO COM SUCESSO", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(context, "ALGO ERRADO", Toast.LENGTH_SHORT).show();
//        }

        /*PASSANDO ESTAS INFORMAÇÕES PARA PROXIMA TELA*/
        int previsaoFim = new CalculadoraPrevisao().calculaPrevisao(peso, pressaoEmagrecimento, pesoIdeal);

        intent.putExtra("nomeTutor", nome_dono);
        intent.putExtra("cpf", cpf);

        intent.putExtra("nomeAnimal", nome_cachorro);
        intent.putExtra("idade", idade);

        intent.putExtra("peso", peso);
        intent.putExtra("pressaoEmagrecimento", Float.toString(totalPressaoEmagrecimento*100));
        intent.putExtra("kcalDia", Float.toString(kcalDia));
        intent.putExtra("dietaRacao", Float.toString(quantidadeRacao));
        intent.putExtra("pesoAPerder", Float.toString(pesoPerdido));
        intent.putExtra("pesoRetorno", Float.toString(peso-pesoPerdido));
        intent.putExtra("pesoIdeal", Float.toString(pesoIdeal));
        intent.putExtra("previsaoFim", Integer.toString(previsaoFim));

        context.startActivity(intent);
    }
}
