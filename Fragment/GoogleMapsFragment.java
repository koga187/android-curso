package br.com.caelum.cadastro.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;
import br.com.caelum.cadastro.DAO.AlunoDAO;
import br.com.caelum.cadastro.Entity.AlunoEntity;
import br.com.caelum.cadastro.Localizador;

public class GoogleMapsFragment extends SupportMapFragment {
    @Override
    public void onResume() {
        super.onResume();
        GoogleMap mapa = getMap();

        Localizador localizador = new Localizador(getActivity());
        AlunoDAO dao = new AlunoDAO(getActivity());

        List<AlunoEntity> alunos = dao.getLista();

        for(AlunoEntity aluno : alunos) {
            LatLng localizacao = localizador.getCordenadas(aluno.getEndereco());

            if(localizacao != null) {
                mapa.addMarker(new MarkerOptions()
                        .title(aluno.getNome())
                        .snippet("nota: "+ aluno.getNota()+"\n"+"Endereço: "+ aluno.getEndereco())
                        .position(localizacao));


                centralizaNo(localizacao);
            }
        }
    }

    private void centralizaNo(LatLng local) {
        GoogleMap mapa = getMap();
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(local, 2);
        mapa.moveCamera(camera);
    }

}
