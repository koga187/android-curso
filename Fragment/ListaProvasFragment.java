package br.com.caelum.cadastro.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.Entity.ProvaEntity;
import br.com.caelum.cadastro.R;

public class ListaProvasFragment extends Fragment {
    private ListView listViewProvas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View layoutProvas = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        ProvaEntity prova1 = new ProvaEntity("20/01/2001", "Matematica");
        prova1.setTopicos(Arrays.asList("algebra", "calculo", "Estatistica"));

        ProvaEntity prova2 = new ProvaEntity("20/01/2001", "Portugues");
        prova2.setTopicos(Arrays.asList("oi", "oi", "oi"));

        List<ProvaEntity> provas = Arrays.asList(prova1, prova2);

        //this.listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_prova);

        this.listViewProvas.setAdapter(new ArrayAdapter<ProvaEntity>(getActivity(),
                android.R.layout.simple_list_item_1, provas));


        return layoutProvas;
    }
}
