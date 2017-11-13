package com.example.willian.projetopurina;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willian on 03/11/17.
 */

public class ListaCachorrosCpfOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        /*TRANSFORMANDO O CONTEXTO EM ACTIVITY*/
        final Activity activity = (Activity) context;

        Intent intent = new Intent(activity, ListaDeCachorros.class);

        /*PEGAR O CPF PASSADO PELO USUARIO E COLCOAR EM UMA STRING*/

        final EditText editTextPesquisaCpf = activity.findViewById(R.id.cpfPesquisa);

        String cpfTutor = editTextPesquisaCpf.getText().toString();

        /*PEGAR STRING QUE POSSUI CPF E COLOCAR NO METODO QUE BUSCA PELO CPF*/
        Cachorro dadosCachorro = new BancoController(context).buscaCPF(cpfTutor);

        intent.putExtra("nomeCachorro", dadosCachorro.getNome_animal());
        intent.putExtra("idCachorro", dadosCachorro.getId());

        context.startActivity(intent);

    }
}
