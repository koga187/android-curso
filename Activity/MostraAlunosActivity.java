package br.com.caelum.cadastro.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import br.com.caelum.cadastro.Fragment.GoogleMapsFragment;
import br.com.caelum.cadastro.R;

public class MostraAlunosActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mostra_aluno);

        GoogleMapsFragment mapaFragment = new GoogleMapsFragment();
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.mostra_alunos_mapa, mapaFragment);
        tx.commit();
    }
}
