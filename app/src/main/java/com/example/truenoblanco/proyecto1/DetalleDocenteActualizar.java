package com.example.truenoblanco.proyecto1;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetalleDocenteActualizar extends Activity {

    ControlBD helper;
    EditText editCodigo;
    EditText editCodigoGrupo;
    EditText editTipoRol;
    EditText editNombreDocente;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_docente_actualizar);
        helper = new ControlBD(this);
        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editCodigoGrupo = (EditText) findViewById(R.id.editCodigoGrupo);
        editTipoRol = (EditText) findViewById(R.id.editTipoRol);
    }
    public void actualizarDetalleDocente(View v) {

        DetalleDocente detalleDocente = new DetalleDocente();
        detalleDocente.setCodigoDocente(editCodigo.getText().toString());
        detalleDocente.setCodigoGrupo(editCodigoGrupo.getText().toString());
        detalleDocente.setTipoRol(editTipoRol.getText().toString());
        detalleDocente.setNombreDocente(editNombreDocente.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(detalleDocente);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editCodigo.setText("");
        editCodigoGrupo.setText("");
        editTipoRol.setText("");
    }


}
