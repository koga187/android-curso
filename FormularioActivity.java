package br.com.caelum.cadastro;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import br.com.caelum.cadastro.DAO.AlunoDAOCaelum;
import br.com.caelum.cadastro.Helpers.FormularioHelper;
import br.com.caelum.cadastro.Entity.AlunoEntity;


public class FormularioActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

                FormularioHelper helper = new FormularioHelper(this);
                AlunoEntity aluno = helper.pegaAlunoFormulario();
                AlunoDAOCaelum dao = new AlunoDAOCaelum(this);
                dao.insere(aluno);
                dao.close();
                finish();

                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
