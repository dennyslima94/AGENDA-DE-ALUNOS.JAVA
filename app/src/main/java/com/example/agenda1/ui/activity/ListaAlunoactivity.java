package com.example.agenda1.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda1.R;
import com.example.agenda1.model.Aluno;
import com.example.agenda1.ui.activity.adapter.ListaDeAlunosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.agenda1.ui.activity.ConstantesActivities.CHAVE_ALUNO;

@SuppressWarnings("ALL")
public class ListaAlunoactivity extends AppCompatActivity {

    private final ListaAlunoView listaAlunoView = new ListaAlunoView(this);
    private ListaDeAlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno);
        setTitle("Lista de Alunos");
        configFabNovoAluno();
        configLista();
        new AlertDialog.Builder(this);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_aluos_menu, menu);

    }

    private void configFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_aluno_fab_novo_aluno);
        //noinspection Convert2Lambda
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInsereAluno();

            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this,
                FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunoView.atualizaAlunos();
    }


    private void configLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_aluno_listvew);
        listaAlunoView.configuraAdapter(listaDeAlunos);
        configListenerDeClickPorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);

    }


    private void configListenerDeClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoescolhido = (Aluno) adapterView.getItemAtPosition(position);
                abreFormularioModoEditaAluno(alunoescolhido);

            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunoactivity.this,
                FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    public boolean onContextItemSelected(final MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_aluno_menu_remover){
            listaAlunoView.confirmaRemoção(item);
        }

        return super.onContextItemSelected(item);
    }



}
