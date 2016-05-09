package com.example.truenoblanco.proyecto1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetalleDocenteInsertar extends Activity {

    ControlBD helper;
    EditText editCodigo;
    EditText editCodigoGrupo;
    EditText editTipoRol;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_docente_insertar);
        helper = new ControlBD(this);
        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editCodigoGrupo = (EditText) findViewById(R.id.editCodGrupo);
        editTipoRol = (EditText) findViewById(R.id.editTipoRol);
    }
    public void insertarDetalleDocente(View v) {
        String regInsertados;
        String codigo=editCodigo.getText().toString();
        String codigogrupo=editCodigoGrupo.getText().toString();
        String tiporol=editTipoRol.getText().toString();

        DetalleDocente detalledocente= new DetalleDocente();
        detalledocente.setCodigoDocente(codigo);
        detalledocente.setCodigoGrupo(codigogrupo);
        detalledocente.setTipoRol(tiporol);
        helper.abrir();
        regInsertados=helper.insertar(detalledocente);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editCodigoGrupo.setText("");
        editTipoRol.setText("");
    }
}
