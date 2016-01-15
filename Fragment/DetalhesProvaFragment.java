package br.com.caelum.cadastro.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.caelum.cadastro.Entity.ProvaEntity;
import br.com.caelum.cadastro.R;

public class DetalhesProvaFragment extends Fragment {

    private TextView materia;
    private TextView data;
    private ListView topicos;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup viewGroup, Bundle bundles) {
        View layoutProva = inflater.inflate(R.layout.fragment_detalhes_prova, viewGroup, false);

        buscaComponente(layoutProva);

        if(getArguments() != null) {
            ProvaEntity prova = (ProvaEntity) getArguments().getSerializable("prova");
            populaCamposComDados(prova);
        }

        buscaComponente(layoutProva);

        return layoutProva;
    }

    private void buscaComponente(View layout) {
        this.materia = (TextView) layout.findViewById(R.id.detalhe_prova_materia);
        this.data = (TextView) layout.findViewById(R.id.detalhe_prova_data);
        this.topicos = (ListView) layout.findViewById(R.id.detalhe_prova_topicos);
    }

    public void populaCamposComDados(ProvaEntity prova) {
        if(prova != null) {
            this.materia.setText(prova.getMateria());
            this.data.setText(prova.getData());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1,
                     prova.getTopicos());
            this.topicos.setAdapter(adapter);
        }

    }
}
