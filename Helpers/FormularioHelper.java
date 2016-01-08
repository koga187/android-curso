package br.com.caelum.cadastro.Helpers;


import android.widget.EditText;
import android.widget.RatingBar;
import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.Entity.AlunoEntity;


public class FormularioHelper {

    private AlunoEntity aluno;

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;

    public FormularioHelper(FormularioActivity activity) {

        this.aluno = new AlunoEntity();

        this.nome = (EditText) activity.findViewById(R.id.nome);
        this.telefone = (EditText) activity.findViewById(R.id.telefone);
        this.site = (EditText) activity.findViewById(R.id.site);
        this.endereco = (EditText) activity.findViewById(R.id.endereco);
        this.nota = (RatingBar) activity.findViewById(R.id.nota);
    }

    public AlunoEntity pegaAlunoFormulario() {
        this.aluno.setNome(nome.getText().toString());
        this.aluno.setTelefone(telefone.getText().toString());
        this.aluno.setEndereco(endereco.getText().toString());
        this.aluno.setSite(site.getText().toString());
        this.aluno.setNota(nota.getProgress());

        return this.aluno;
    }

    public void insereDadosFormulario(AlunoEntity aluno) {
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());

        this.aluno = aluno;
    }
}
