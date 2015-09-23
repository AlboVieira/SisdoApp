package br.com.albovieira.sisdoapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.albovieira.sisdoapp.Dao.UsuarioDao;

public class MainActivity extends AppCompatActivity {

    private static final String MANTER_CONECTADO = "manter_conectado";
    private EditText nome, email, senha , usuario;
    private CheckBox perfil,manterConectado;
    private String id;

    private UsuarioDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
        manterConectado = (CheckBox) findViewById(R.id.manterConectado);

        //se estiver setado nas preferencias para manter conectado , abre a intent dashboard
        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        boolean conectado = preferencias.getBoolean(MANTER_CONECTADO,false);
        if(conectado)
            startActivity(new Intent(this,DashBoardActivity.class));


        dao = new UsuarioDao(this);
        dao.getDb();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View v){

        String usuarioInformado = usuario.getText().toString();
        String senhaInformada = senha.getText().toString();

        if("admin".equals(usuarioInformado) && "123".equals(senhaInformada)){
            //aplica as configurações(caso nao esteja aplicado) ao clicar em entrar ,
            //com o editor e possivel setar dados
            SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putBoolean(MANTER_CONECTADO,this.manterConectado.isChecked());
            editor.apply();

            startActivity(new Intent(this,DashBoardActivity.class));
        }
        else{
            String mensagemErro = getString(R.string.erro_autenticacao);
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void cadastrarUsuario(View v) {
        startActivity(new Intent(this,CadastroUsuarioActivity.class));
    }

}

