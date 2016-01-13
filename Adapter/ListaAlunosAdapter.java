package br.com.caelum.cadastro.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import br.com.caelum.cadastro.Entity.AlunoEntity;
import br.com.caelum.cadastro.R;

public class ListaAlunosAdapter extends BaseAdapter
{
    private Activity activity;
    private List<AlunoEntity> alunos;

    public ListaAlunosAdapter(Activity activity, List<AlunoEntity> alunos) {
        this.activity = activity;
        this.alunos = alunos;
    }

    public View getView(int posicao, View contentView, ViewGroup parent) {
        View view =  activity.getLayoutInflater().inflate(R.layout.item_lista, parent, false);

        AlunoEntity aluno = alunos.get(posicao);

        ImageView image = (ImageView) view.findViewById(R.id.item_foto);

        Bitmap bm;

        if (aluno.getCaminhoFoto() == null) {
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        } else {
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        }

        bm = Bitmap.createScaledBitmap(bm, 50, 50, true);
        image.setImageBitmap(bm);

        TextView text = (TextView)view.findViewById(R.id.list_text);
        text.setText(aluno.getNome());

        if(posicao % 2 == 0) {
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        } else {
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
        }

        return view;
    }

    public int getCount() {
        return alunos.size();
    }

    public Object getItem(int posicao) {
        return alunos.get(posicao);
    }

    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }
}

