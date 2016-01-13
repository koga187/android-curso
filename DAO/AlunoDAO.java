package br.com.caelum.cadastro.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import br.com.caelum.cadastro.Entity.AlunoEntity;

public class AlunoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 3;
    private static final String TABELA = "Alunos";
    private static final String DATABASE = "CadastroCaelum";

    public AlunoDAO(Context ctx) {
        super(ctx, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE "+ TABELA +
                "(id INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "telefone TEXT," +
                "endereco TEXT," +
                "site TEXT," +
                "nota REAL," +
                "caminho_foto TEXT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versaoAntiga,  int versaoNova) {
        database.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(database);

    }

    public void insere(AlunoEntity aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("caminho_foto", aluno.getCaminhoFoto());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<AlunoEntity> getLista() {
        Cursor cursor = getReadableDatabase()
                .rawQuery("SELECT " +
                        "id, " +
                        "nome, " +
                        "telefone, " +
                        "endereco, " +
                        "site, " +
                        "nota, " +
                        "caminho_foto " +
                        "FROM " + TABELA + ";", null);

        List<AlunoEntity> alunos = new ArrayList<>();

        while(cursor.moveToNext()) {
            AlunoEntity aluno = new AlunoEntity();

            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminho_foto")));

            alunos.add(aluno);
        }

        cursor.close();

        return alunos;
    }

    public void altera(AlunoEntity aluno) {
        ContentValues values = new ContentValues();

        values.put("id", aluno.getId());
        values.put("nome", aluno.getNome());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("caminho_foto", aluno.getCaminhoFoto());

        getWritableDatabase().update(TABELA, values, "id=?", new String[] {aluno.getId().toString()});
    }

    public void deletar(AlunoEntity aluno) {
        String[] args = {aluno.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", args);
    }

    public boolean isAluno(String telefone) {

        String[] parametros = {telefone};

        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT telefone FROM " +  TABELA +
        " WHERE telefone = ?", parametros);

        int total = rawQuery.getColumnCount();
        rawQuery.close();

        return total > 0;

    }
}
