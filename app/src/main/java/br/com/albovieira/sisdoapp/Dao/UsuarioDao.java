package br.com.albovieira.sisdoapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.albovieira.sisdoapp.Constante.UsuarioConst;
import br.com.albovieira.sisdoapp.Entidade.Usuario;

/**
 * Created by albov on 29/08/2015.
 */
public class UsuarioDao extends SisdoDao{

    public UsuarioDao(Context context) {
        super(context);
    }

    public long insert(Usuario usuario){
        ContentValues values = new ContentValues();
        //values.put(UsuarioConst.ID, usuario.getId());
        values.put(UsuarioConst.EMAIL, usuario.getEmail());
        values.put(UsuarioConst.SENHA, usuario.getSenha());
        values.put(UsuarioConst.PERFIL, usuario.getPerfil());
        values.put(UsuarioConst.NOME, usuario.getNome());

        return getDb().insert(UsuarioConst.NOME_TABELA, null, values);
    }

    public long update(Usuario usuario){
        ContentValues values = new ContentValues();
        //values.put(UsuarioConst.ID, usuario.getId());
        values.put(UsuarioConst.EMAIL, usuario.getEmail());
        values.put(UsuarioConst.SENHA, usuario.getSenha());
        values.put(UsuarioConst.PERFIL, usuario.getPerfil());
        values.put(UsuarioConst.NOME, usuario.getNome());

        return getDb().update(UsuarioConst.NOME_TABELA, values, null, null);
    }

    public boolean remover(Long id){
        String whereClause = UsuarioConst.ID + " = ?";
        String[] whereArgs = new String[]{id.toString()};
        int removidos = getDb().delete(UsuarioConst.NOME_TABELA,
                whereClause, whereArgs);
        return removidos > 0;
    }

    public List<Usuario> listarUsuarios(){
        Cursor cursor = getDb().query(UsuarioConst.NOME_TABELA,
                UsuarioConst.COLUNAS,
                null, null, null, null, null);
        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(cursor.moveToNext()){
            Usuario usuario = new Usuario(
                    cursor.getLong(cursor.getColumnIndex(UsuarioConst.ID)),
                    cursor.getString(cursor.getColumnIndex(UsuarioConst.NOME)),
                    cursor.getString(cursor.getColumnIndex(UsuarioConst.EMAIL)),
                    cursor.getString(cursor.getColumnIndex(UsuarioConst.SENHA)),
                    cursor.getLong(cursor.getColumnIndex(UsuarioConst.PERFIL))
            );
            usuarios.add(usuario);
        }
        cursor.close();
        return usuarios;
    }

    public Usuario getUsuarioByEmail(String email){
        Cursor cursor = getDb().query(UsuarioConst.NOME_TABELA,
                UsuarioConst.COLUNAS,
                UsuarioConst.EMAIL + " = ?",
                new String[]{email},
                null, null, null);
        if(cursor.moveToNext()){
            Usuario usuario = new Usuario(
                    cursor.getLong(cursor.getColumnIndex(UsuarioConst.ID)),
                    cursor.getString(cursor.getColumnIndex(UsuarioConst.NOME)),
                    cursor.getString(cursor.getColumnIndex(UsuarioConst.EMAIL)),
                    cursor.getString(cursor.getColumnIndex(UsuarioConst.SENHA)),
                    cursor.getLong(cursor.getColumnIndex(UsuarioConst.PERFIL))
            );
            cursor.close();
            return usuario;
        }
        return null;
    }

}
