package br.com.caelum.cadastro.Task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.Converter.AlunoConverter;
import br.com.caelum.cadastro.DAO.AlunoDAO;
import br.com.caelum.cadastro.Entity.AlunoEntity;
import br.com.caelum.cadastro.WebClient.WebClient;

/*** ---------------------------------------------------------------- ***
 * #### AsyncTask ####
 * 1 - primeiro valor tem que ser o recebido no método doInBackground
 *
 * 3 - terceiro valor, espera que seja retornado no método doInBackGround e recebido como parametro
 * no onPostExecute
 */
public class EnviarAlunosTask extends AsyncTask<Object, Object, String>{
    private Context context;
    private ProgressDialog progressDialog;

    public EnviarAlunosTask(Context context) {
        this.context = context;
    }

    public String doInBackground(Object... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<AlunoEntity> alunos = dao.getLista();
        dao.close();
        String json = new AlunoConverter().toJson(alunos);

        WebClient client = new WebClient();
        String response = client.post(json);

        return response;
    }

    public void onPostExecute(String resp) {
        Toast.makeText(context, resp, Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }

    public void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Aguarde...", "Enviando", true, true);
    }
}
