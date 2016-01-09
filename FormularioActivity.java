package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import br.com.caelum.cadastro.DAO.AlunoDAOCaelum;
import br.com.caelum.cadastro.Helpers.FormularioHelper;
import br.com.caelum.cadastro.Entity.AlunoEntity;


public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
        Intent intent = getIntent();

        AlunoEntity aluno = (AlunoEntity) intent.getSerializableExtra("alunoSelecionado");

        if(aluno != null) {
            this.helper.insereDadosFormulario(aluno);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AlunoDAOCaelum dao = new AlunoDAOCaelum(this);
        AlunoEntity aluno = helper.pegaAlunoFormulario();

        if(aluno.getId() != null) {

            dao.altera(aluno);
        } else {
            dao.insere(aluno);
        }

        dao.close();
        finish();

        return false;
    }
}
