package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

import br.com.caelum.cadastro.DAO.AlunoDAOCaelum;
import br.com.caelum.cadastro.Helpers.FormularioHelper;
import br.com.caelum.cadastro.Entity.AlunoEntity;


public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;
    private String localArquivoFoto;
    private static final Integer IR_PARA_CAMERA = 123;

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

        Button foto = helper.getFotoButton();

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";

                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri localFoto = Uri.fromFile(new File(localArquivoFoto));
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);

                startActivityForResult(irParaCamera, IR_PARA_CAMERA);
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IR_PARA_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {

            }
            else {
                this.localArquivoFoto = null;
            }
            helper.carregaImagem(this.localArquivoFoto);
        }
    }
}
