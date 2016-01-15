package br.com.caelum.cadastro;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Localizador {
    private Geocoder geo;

    public Localizador(Context context) {
        this.geo = new Geocoder(context);
    }

    public LatLng getCordenadas(String Endereco) {
        try{
            List<Address> cordenadas = geo.getFromLocationName(Endereco, 1);

            if(cordenadas != null) {
                Address cordenada = cordenadas.get(0);
                return new LatLng(cordenada.getLatitude(), cordenada.getLongitude());
            }

        } catch (Exception exception) {

        }

        return null;
    }
}
