package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.DAO.AlunoDAOCaelum;
import br.com.caelum.cadastro.Entity.AlunoEntity;


public class ListaAlunosActivity extends Activity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        AlunoDAOCaelum dao = new AlunoDAOCaelum(this);
        List<AlunoEntity> alunos = dao.getLista();

        this.listaAlunos = (ListView) findViewById(R.id.lista_aluno);

        ArrayAdapter<AlunoEntity> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunos);

        listaAlunos.setAdapter(adapter);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaAlunosActivity.this, "Item posição: "+position, Toast.LENGTH_LONG).show();
            }
        });

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String aluno = (String) parent.getItemAtPosition(position);
                Toast.makeText(ListaAlunosActivity.this, "Aluno:"+aluno, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        Button inserir = (Button) findViewById(R.id.floatingButton);

        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListaAlunosActivity.this, "Pegadinha!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
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

    @Override
    protected void onResume() {
        super.onResume();
        CarregaLista();

    }

    public void CarregaLista() {
        AlunoDAOCaelum dao = new AlunoDAOCaelum(this);
        List<AlunoEntity> alunos = dao.getLista();
        dao.close();

        ArrayAdapter<AlunoEntity> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunos);

        this.listaAlunos.setAdapter(adapter);


    }

    public void onCreateContextMenu(ContextMenu menu, View view, MenuI) {

    }
}
