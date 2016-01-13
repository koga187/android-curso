package br.com.caelum.cadastro.Converter;

import org.json.JSONStringer;

import java.util.List;

import br.com.caelum.cadastro.Entity.AlunoEntity;

public class AlunoConverter {
    public String toJson(List<AlunoEntity> alunos) {
        JSONStringer jsonStringer = new JSONStringer();

        try {

            jsonStringer.object().key("list").array()
                    .object().key("aluno").array();

            for(AlunoEntity aluno : alunos) {
                jsonStringer.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("telefone").value(aluno.getTelefone())
                        .key("site").value(aluno.getSite())
                        .key("endereco").value(aluno.getEndereco())
                        .key("nota").value(aluno.getNota())
                        .key("caminhoFoto").value(aluno.getCaminhoFoto());
            }

            jsonStringer.endArray().endObject().endArray().endObject().toString();
        } catch (Exception exception) {

        }

        return jsonStringer.toString();
    }
}
