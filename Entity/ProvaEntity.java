package br.com.caelum.cadastro.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProvaEntity implements Serializable {
    private String data;
    private String materia;
    private List<String> topicos = new ArrayList<String>();

    public ProvaEntity (String data, String materia) {
        this.data = data;
        this.materia = materia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    @Override
    public String toString() {
        return this.getMateria();
    }
}
