package com.example.agenda1.dao;

import com.example.agenda1.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contID = 1;

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }




    public void salva(Aluno aluno) {
        aluno.setId(contID);
        alunos.add(aluno);
        atualizaIds();
    }

    private void atualizaIds() {
        contID++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);
        if (alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }

    }

    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if(alunoDevolvido != null) {
            alunos.remove(alunoDevolvido);
        }
    }
}
