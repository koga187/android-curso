package br.com.caelum.cadastro.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.caelum.cadastro.Adapter.ListaAlunosAdapter;
import br.com.caelum.cadastro.DAO.AlunoDAO;
import br.com.caelum.cadastro.Entity.AlunoEntity;
import br.com.caelum.cadastro.R;


public class ListaAlunosActivity extends Activity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        AlunoDAO dao = new AlunoDAO(this);
        List<AlunoEntity> alunos = dao.getLista();

        this.listaAlunos = (ListView) findViewById(R.id.lista_aluno);

        ArrayAdapter<AlunoEntity> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alunos);

        listaAlunos.setAdapter(adapter);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                AlunoEntity alunoSelecionado = (AlunoEntity) listaAlunos.getItemAtPosition(position);
                edicao.putExtra("alunoSelecionado", alunoSelecionado);

                startActivity(edicao);
            }
        });

        registerForContextMenu(listaAlunos);

        Button inserir = (Button) findViewById(R.id.floatingButton);
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CarregaLista();

    }

    public void CarregaLista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<AlunoEntity> alunos = dao.getLista();
        dao.close();

        ListaAlunosAdapter adapter = new ListaAlunosAdapter(this, alunos);

        this.listaAlunos.setAdapter(adapter);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        final AlunoEntity alunoSelecionado = (AlunoEntity) listaAlunos.getItemAtPosition(info.position);

        MenuItem ligar = menu.add("Ligar");
        Intent IntentLigar = new Intent(Intent.ACTION_CALL);
        IntentLigar.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
        ligar.setIntent(IntentLigar);

        MenuItem sms = menu.add("SMS");
        Intent IntentSMS = new Intent(Intent.ACTION_VIEW);
        IntentSMS.setData(Uri.parse("sms:"+alunoSelecionado.getTelefone()));
        IntentSMS.putExtra("sms_body", "s2 Send By Koga App.");
        sms.setIntent(IntentSMS);

        MenuItem mapa = menu.add("Achar no Mapa");
        Intent IntentMapa = new Intent(Intent.ACTION_VIEW);
        IntentMapa.setData(Uri.parse("geo:0,0?z=14&q=:"+alunoSelecionado.getEndereco()));
        mapa.setIntent(IntentMapa);

        MenuItem site = menu.add("Navegar no site");
        Intent IntentSite = new Intent(Intent.ACTION_VIEW);
        IntentSite.setData(Uri.parse("http:"+alunoSelecionado.getSite()));
        site.setIntent(IntentSite);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deletar(alunoSelecionado);
                dao.close();
                CarregaLista();

                return false;
            }
        });
    }
}
