package br.com.caelum.cadastro.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import br.com.caelum.cadastro.Fragment.ListaProvasFragment;
import br.com.caelum.cadastro.R;

public class ProvasActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.provas_view, new ListaProvasFragment());

        transaction.commit();
    }
}
