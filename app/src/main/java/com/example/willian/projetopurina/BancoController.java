package com.example.willian.projetopurina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willian on 20/09/17.
 */

public class BancoController extends CriaBanco {

    public BancoController (Context context) {
        super(context);
    }

    /*INSERE PESSOA DADOS NO BANCO*/
    public boolean createPessoa (Pessoa pessoa) {

        /*DADOS À SEREM INSERIDOS*/
        ContentValues values = new ContentValues();

        /*INSERIR DADOS PESSOA*/
        values.put("cpf", pessoa.getCpf());
        values.put("nome_dono", pessoa.getNome_dono());

        /*PERMISSAO PARA ESCRITA NO BANCO*/
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("pessoa", null, values) > 0;
        db.close();

        return isCreate;
    }

    /*INSERE CACHORRO DADOS NO BANCO*/
    public int createCachorro (Cachorro cachorro) {

        /*DADOS À SEREM INSERIDOS*/
        ContentValues values = new ContentValues();

        /*INSERIR DADOS CACHORRO*/
        values.put("nome_animal", cachorro.getNome_animal());
        values.put("idade", cachorro.getIdade());
        values.put("sexo", cachorro.getSexo());
        values.put("raca", cachorro.getRaca());
        values.put("score", cachorro.getScore());
        values.put("cpf_pessoa", cachorro.getCpf_pessoa());

        /*PERMISSAO PARA ESCRITA NO BANCO*/
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("cachorro", null, values) > 0;

        int id = -1;

        if(isCreate) {
            Cursor cur = db.rawQuery("select last_insert_rowid();", null);
            cur.moveToFirst();
            id = cur.getInt(0);
        }

        return id;
    }


    /*INSERE DADOS CALCULADOS PARA RELATÓRIO DO CACHORRO*/
    public boolean createRelatorio (Relatorio relatorio) {

        /*DADOS À SEREM INSERIDOS*/
        ContentValues values = new ContentValues();

        /*INSERIR DADOS DO RELATÓRIO*/
        values.put("id_cachorro", relatorio.getId());
        values.put("mes", relatorio.getData());
        values.put("peso", relatorio.getPeso());
        values.put("pressao_emagrecimento", relatorio.getPressao_emagrecimento());
        values.put("peso_perdido", relatorio.getPeso_perdido());
        values.put("kcal_perdidas_mes", relatorio.getKcal_perdidas_mes());
        values.put("kcal_perdidas_dia", relatorio.getKcal_perdidas_dia());
        values.put("necessidade_kcal_dia", relatorio.getNecessidade_de_kcal_dia());
        values.put("kcal_fornecida_dia", relatorio.getKcal_fornecida_dia());
        values.put("taxa_metabolica", relatorio.getTaxa_metabolica());

        /*PERMISSAO PARA ESCRITA NO BANCO*/
        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("relatorio", null, values) > 0;
        db.close();

        return  isCreate;
    }

    /*FUNÇÃO PARA BUSCAR O TUTOR POR CPF NA FAZER DE ADMINISTRAÇÃO DE DADOS*/
//    public List<Cachorro> listaCachorro(String cpf) {
//
//        List<Cachorro> listaCachorros = new ArrayList<>();
//
//        String sql_select_cpf = "SELECT "+NOME_ANIMAL+ID+" FROM "
//                +TABELA_CACHORRO+" JOIN "+TABELA_PESSOA+
//                " ON "+cpf+" = "+CPF_PESSOA+
//                ")";
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(sql_select_cpf, null);
//
//        if (cursor.moveToFirst()) {
//
//            do {
//
//                String stringNomeCachorro = cursor.getString(cursor.getColumnIndex(NOME_ANIMAL));
//                int intId = cursor.getInt(cursor.getColumnIndex(ID));
//
//                Cachorro cachorro = new Cachorro();
//                cachorro.setNome_animal(stringNomeCachorro);
//
//                listaCachorros.add(cachorro);
//
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//
//        return listaCachorros;
//    }

    /**/
    public Cachorro buscaCPF (String cpf) {

        final Cachorro cachorro = new Cachorro();
//        final Relatorio relatorio = new Relatorio();
//        final Pessoa pessoa = new Pessoa();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql_BuscaCpf = "SELECT "+NOME_ANIMAL+", "+ID+" FROM "
                +TABELA_CACHORRO+" JOIN "+TABELA_PESSOA+
                " ON "+cpf+" = "+CPF_PESSOA;

        Cursor cursor = db.rawQuery(sql_BuscaCpf, null);

        if (cursor.moveToFirst()) {

//            String nomeTutor = cursor.getString(cursor.getColumnIndex(NOME_DONO));
            String nomeAnimal = cursor.getString(cursor.getColumnIndex(NOME_ANIMAL));
            int idAnimal = cursor.getInt(cursor.getColumnIndex(ID));

//            pessoa.setNome_dono(nomeTutor);
            cachorro.setNome_animal(nomeAnimal);
            cachorro.setId(idAnimal);
        }
        return cachorro;
    }

    public Relatorio recuperarRelatorio (int id) {

        final Relatorio relatorio = new Relatorio();

        SQLiteDatabase db = this.getReadableDatabase();

        /*LEMBRAR DE VERIFICAR AQUI MESMO O NUMERO DE LINHAS DA TABELA DE ACORDO COM O ID
        * ISSO SERVIRA PARA VERIFICAR SE A PRESSAO DE EMAGRECIMENTO IRA SER ALTERADA DE
        * ACORDO COM O MES*/
        String sql_BuscaId = "SELECT "+PRESSAO_EMAGRECIMENTO+" FROM "
                +TABELA_CACHORRO+" JOIN "+TABELA_RELATORIO+
                " ON "+id+" = "+ID_CACHORRO;

        Cursor cursor = db.rawQuery(sql_BuscaId,null);

        if (cursor.moveToLast()) {

            String txtPressaoEmagrecimento = cursor.getString(cursor.getColumnIndex(PRESSAO_EMAGRECIMENTO));

            relatorio.setPressao_emagrecimento(txtPressaoEmagrecimento);
        }

        return relatorio;
    }
}