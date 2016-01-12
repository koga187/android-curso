package br.com.caelum.cadastro.Adapter;

import android.app.Activity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import br.com.caelum.cadastro.Entity.AlunoEntity;
import br.com.caelum.cadastro.R;

abstract public class ListaAlunosAdapter extends BaseAdapter
{
    private Activity activity;
    private List<AlunoEntity> alunos;

    public void ListaAlunosAdapter(Activity activity, List<AlunoEntity> alunos) {
        this.activity = activity;
        this.alunos = alunos;
    }

    public View getView(int posicao) {
        View view =  activity.getLayoutInflater().inflate(R.layout.item_lista);

        ImageView image = view.findViewById(R.id.item_foto);


    }
}

