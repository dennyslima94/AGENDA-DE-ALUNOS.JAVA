package com.example.agenda1.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.example.agenda1.dao.AlunoDAO;
import com.example.agenda1.model.Aluno;
import com.example.agenda1.ui.activity.adapter.ListaDeAlunosAdapter;

public class ListaAlunoView {

    private final ListaDeAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;


    public ListaAlunoView(Context context) {
        this.context = context;
        adapter = new ListaDeAlunosAdapter(this.context) ;
        dao = new AlunoDAO();
    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    public void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }

    public void confirmaRemoção(MenuItem item) {
        new AlertDialog.Builder(this.context)
                .setTitle("Excluindo aluno")
                .setMessage("Tem certeza que deseja excluir Aluno?").setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolido = adapter.getItem(menuInfo.position);
                      remove(alunoEscolido);
                    }

                }).setNegativeButton("Não", null)
                .show();
    }




}
