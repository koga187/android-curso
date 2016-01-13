package br.com.caelum.cadastro.Helpers;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import br.com.caelum.cadastro.Activity.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.Entity.AlunoEntity;


public class FormularioHelper {

    private AlunoEntity aluno;

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;
    private ImageView foto;
    private Button fotoButton;

    public FormularioHelper(FormularioActivity activity) {

        this.aluno = new AlunoEntity();

        this.nome = (EditText) activity.findViewById(R.id.nome);
        this.telefone = (EditText) activity.findViewById(R.id.telefone);
        this.site = (EditText) activity.findViewById(R.id.site);
        this.endereco = (EditText) activity.findViewById(R.id.endereco);
        this.nota = (RatingBar) activity.findViewById(R.id.nota);
        this.foto = (ImageView) activity.findViewById(R.id.formulario_foto);
        this.fotoButton = (Button) activity.findViewById(R.id.formulario_foto_button);
    }

    public AlunoEntity pegaAlunoFormulario() {
        this.aluno.setNome(nome.getText().toString());
        this.aluno.setTelefone(telefone.getText().toString());
        this.aluno.setEndereco(endereco.getText().toString());
        this.aluno.setSite(site.getText().toString());
        this.aluno.setNota(nota.getProgress());
        this.aluno.setCaminhoFoto((String) foto.getTag());

        return this.aluno;
    }

    public void insereDadosFormulario(AlunoEntity aluno) {
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());

        if(aluno.getCaminhoFoto() != null) {
            carregaImagem(aluno.getCaminhoFoto());
        }

        this.aluno = aluno;
    }

    public void carregaImagem(String localFoto) {

        Bitmap bm = BitmapFactory.decodeFile(localFoto);
        Bitmap bmReduzido = Bitmap.createScaledBitmap(bm, 400, 300, false);

        foto.setImageBitmap(bmReduzido);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
        foto.setTag(localFoto);

    }

    public Button getFotoButton() {
        return fotoButton;
    }
}
