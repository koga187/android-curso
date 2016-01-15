package br.com.caelum.cadastro.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.cadastro.Entity.ProvaEntity;
import br.com.caelum.cadastro.Fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.Fragment.ListaProvasFragment;
import br.com.caelum.cadastro.R;

public class ProvasActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(isTablet()) {
            transaction.replace(R.id.provas_lista, new ListaProvasFragment());
            transaction.replace(R.id.provas_detalhes, new DetalhesProvaFragment());
        } else {
            transaction.replace(R.id.provas_view, new ListaProvasFragment());
        }

        transaction.commit();
    }

    private Boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    public void SelecionaProva (ProvaEntity prova) {
        FragmentManager manager = getSupportFragmentManager();

        if(isTablet()) {
            DetalhesProvaFragment detalhesProva = (DetalhesProvaFragment) manager.findFragmentById(R.id.provas_detalhes);

            detalhesProva.populaCamposComDados(prova);
        } else {
            Bundle argumentos = new Bundle();
            argumentos.putSerializable("prova", prova);

            DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
            detalhesProva.setArguments(argumentos);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.provas_view, detalhesProva);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }

}
