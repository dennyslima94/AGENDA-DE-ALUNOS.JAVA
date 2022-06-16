package com.example.agenda1;

import android.app.Application;

import com.example.agenda1.dao.AlunoDAO;
import com.example.agenda1.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CriaAlunoTeste();
    }

    private void CriaAlunoTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("fran","22","22@"));
        dao.salva(new Aluno("alex","11","11@"));
    }
}
