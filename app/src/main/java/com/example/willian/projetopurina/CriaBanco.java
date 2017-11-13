package com.example.willian.projetopurina;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by willian on 20/09/17.
 */

public class CriaBanco extends SQLiteOpenHelper {

    /*VERSAO DO BANCO DE DADOS*/
    private static final int DATABASE_VERSION = 1;

    /* NOME DO BANCO */
    protected static final String NOME_BANCO = "emagrecimento_cachorro.db";

    /* TABELA PESSOA + ATRIBUTOS */
    public static final String TABELA_PESSOA = "pessoa";

    public static final String CPF = "cpf";
    public static final String NOME_DONO = "nome_dono";

    /* TABELA CACHORRO + ATRIBUTOS */
    public static final String TABELA_CACHORRO = "cachorro";

    public static final String ID = "id";
    public static final String NOME_ANIMAL = "nome_animal";
    public static final String IDADE = "idade";
    public static final String SEXO = "sexo";
    public static final String RACA = "raca";
    public static final String PESO_IDEAL = "pesoIdeal";
    public static final String CPF_PESSOA = "cpf_pessoa";

    /* TABELA RELATORIO + ATRIBUTOS */
    public static final String TABELA_RELATORIO = "relatorio";

    public static final String ID_RELATORIO = "id_relatorio";
    public static final String ID_CACHORRO = "id_cachorro";
    public static final String MES = "mes";
    public static final String PESO = "peso";
    public static final String PRESSAO_EMAGRECIMENTO = "pressao_emagrecimento";
    public static final String PESO_PERDIDO = "peso_perdido";
    public static final String KCAL_PERDIDAS_MES = "kcal_perdidas_mes";
    public static final String KCAL_PERDIDAS_DIA = "kcal_perdidas_dia";
    public static final String NECESS_KCAL_DIA = "necessidade_kcal_dia";
    public static final String KCAL_FORNECIDA_DIA = "kcal_fornecida_dia";
    public static final String TAXA_METABOLICA_BASAL = "taxa_metabolica";
    public static final String QUANTIDADE_RACAO = "quantidade_racao";

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*CRIAÇÃO DA TABELA PESSOA*/
        String sql_criaPessoa = "CREATE TABLE "+TABELA_PESSOA+"(" +
                CPF+" TEXT PRIMARY KEY, "+
                NOME_DONO+" TEXT"+
                ")";

        /*CRIAÇÃO DA TABELA CACHORRO*/
        String sql_criaCachorro = "CREATE TABLE "+TABELA_CACHORRO+"("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NOME_ANIMAL+" TEXT, "+
                IDADE+" INTEGER, "+
                RACA+" TEXT, "+
                SEXO+" TEXT, "+
                PESO_IDEAL+" TEXT, "+
                CPF_PESSOA+" TEXT, "+
                "CONSTRAINT fk_cpf_pessoa FOREIGN KEY("+CPF_PESSOA+") REFERENCES "+TABELA_PESSOA+"("+CPF+"))";

        /*CRIACAO DA TABELA RELATORIO*/
        String sql_criaRelatorio = "CREATE TABLE "+TABELA_RELATORIO+"("+
                ID_RELATORIO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ID_CACHORRO+" INTEGER, "+
                MES+" TEXT, "+
                PESO+" TEXT, "+
                PRESSAO_EMAGRECIMENTO+" TEXT, "+
                PESO_PERDIDO+" TEXT, "+
                KCAL_PERDIDAS_MES+" TEXT, "+
                KCAL_PERDIDAS_DIA+" TEXT, "+
                NECESS_KCAL_DIA+" TEXT, "+
                KCAL_FORNECIDA_DIA+" TEXT, "+
                TAXA_METABOLICA_BASAL+" TEXT, "+
                QUANTIDADE_RACAO+" TEXT"+
                ")";

//        db.execSQL("PRAGMA foreign_keys=ON;");
        db.execSQL(sql_criaPessoa);
        db.execSQL(sql_criaCachorro);
        db.execSQL(sql_criaRelatorio);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*DROP PARA A TABELAS PARA EXECUTAR UM UPGRADE NAS TABELAS*/
        String sql_dropRelatorio = "DROP TABLE IF EXIST "+TABELA_RELATORIO;
        String sql_dropCachorro = "DROP TABLE IF EXIST "+TABELA_CACHORRO;
        String sql_dropPessoa = "DROP TABLE IF EXIST "+TABELA_PESSOA;

        /*EXECUÇÃO DAS SQLS*/
        db.execSQL(sql_dropRelatorio);
        db.execSQL(sql_dropCachorro);
        db.execSQL(sql_dropPessoa);

        /*RECRIA AS TABELAS COM SUAS RESPECTIVAS ALTERAÇÕES NO MÉTODO onCreate*/
        onCreate(db);
    }
}

