package br.com.albovieira.sisdoapp.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.albovieira.sisdoapp.Constante.PerfilConst;
import br.com.albovieira.sisdoapp.Constante.UsuarioConst;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DE_DADOS = "Sisdo";
    private static final int VERSAO = 1;

    public DatabaseHelper(Context context) {
        super(context,BANCO_DE_DADOS, null, VERSAO);
    }

    //na primeira vez q o banco for acessado cria as tabelas
    //se atentar para a coluna _id , o android espera q a chave primaria tenha esse nome
    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder queryPerfil = new StringBuilder();
        queryPerfil.append("CREATE TABLE " + PerfilConst.nomeTabela);
        queryPerfil.append(" (");
        queryPerfil.append(PerfilConst.id + " INTEGER PRIMARY KEY,");
        queryPerfil.append(PerfilConst.perfil + " TEXT");
        queryPerfil.append(" );");
        db.execSQL(queryPerfil.toString());

        StringBuilder queryUsuario = new StringBuilder();
        queryUsuario.append("CREATE TABLE " + UsuarioConst.nomeTabela);
        queryUsuario.append(" (");
            queryUsuario.append(UsuarioConst.id + " INTEGER PRIMARY KEY,");
            queryUsuario.append(UsuarioConst.nome + " TEXT,");
            queryUsuario.append(UsuarioConst.email + " TEXT,");
            queryUsuario.append(UsuarioConst.senha + " TEXT,");
            queryUsuario.append( UsuarioConst.perfil + " INTEGER," +
                    " FOREIGN KEY("+ UsuarioConst.perfil + ") " +
                            "REFERENCES " + PerfilConst.nomeTabela + "("+PerfilConst.id+")");
        queryUsuario.append(" );");
        db.execSQL(queryUsuario.toString());


    /*    StringBuilder queryDoacao = new StringBuilder();
        queryDoacao.append("CREATE TABLE " + UsuarioConst.nomeTabela);
        queryDoacao.append(" (");
        queryDoacao.append(UsuarioConst.id + " INTEGER PRIMARY KEY,");
        queryDoacao.append(UsuarioConst.nome + " TEXT,");
        queryDoacao.append(UsuarioConst.email + " TEXT,");
        queryDoacao.append(UsuarioConst.senha + " TEXT,");
        queryDoacao.append(" FOREIGN KEY(" + UsuarioConst.perfil + ") " +
                "REFERENCES " + PerfilConst.nomeTabela + "(" + PerfilConst.id + ")");
        queryDoacao.append(" );");
        //db.execSQL(queryDoacao.toString());
*/
       /* db.execSQL("CREATE TABLE usuario (_id INTEGER PRIMARY KEY," +
                " destino TEXT, tipo_viagem INTEGER, data_chegada DATE," +
                " data_saida DATE, orcamento DOUBLE," +
                " quantidade_pessoas INTEGER);");

        db.execSQL("CREATE TABLE gasto (_id INTEGER PRIMARY KEY," +
                " categoria TEXT, data DATE, valor DOUBLE," +
                " descricao TEXT, local TEXT, viagem_id INTEGER," +
                " FOREIGN KEY(viagem_id) REFERENCES viagem(_id));");
                */
    }

    //no caso de alteração na estrutura do banco de dados e feito neste metodo
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
