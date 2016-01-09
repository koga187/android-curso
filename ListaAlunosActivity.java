package br.com.caelum.cadastro;

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
import android.widget.ListView;
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

                Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                AlunoEntity alunoSelecionado = (AlunoEntity) listaAlunos.getItemAtPosition(position);
                edicao.putExtra("alunoSelecionado", alunoSelecionado);

                startActivity(edicao);
            }
        });

        registerForContextMenu(listaAlunos);
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


                AlunoDAOCaelum dao = new AlunoDAOCaelum(ListaAlunosActivity.this);
                dao.deletar(alunoSelecionado);
                dao.close();
                CarregaLista();

                return false;
            }
        });
    }
}
