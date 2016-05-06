package com.example.truenoblanco.proyecto1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Docente extends AppCompatActivity {


    private int codigoDocente;
    private int nombreDocente;
    private int apellidoDocente;
    private String escuela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente);
    }

    public int getCodigoDocente() {
        return codigoDocente;
    }

    public void setCodigoDocente(int codigoDocente) {
        this.codigoDocente = codigoDocente;
    }

    public int getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(int nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public int getApellidoDocente() {
        return apellidoDocente;
    }

    public void setApellidoDocente(int apellidoDocente) {
        this.apellidoDocente = apellidoDocente;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }
}


