package com.example.truenoblanco.proyecto1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by TruenoBlanco on 5/5/2016.
 */
public class ControlBD {

    private static final String[]camposDocente = new String [] {"codigodocente","nombredocente","apellidodocente","escuela"};
    private static final String[]camposDetalleDocente = new String [] {"codigodocente","codigogrupo","tiporol","nombrerol"};


    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

    }

    public Docente consultarDocente(String codigo) {
        String[] id = {codigo};

        Cursor cursor = db.query("docente", camposDocente, "codigodocente = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Docente docente = new Docente();
            docente.setCodigoDocente(cursor.getString(0));
            docente.setNombreDocente(cursor.getString(1));
            docente.setApellidoDocente(cursor.getString(2));
            docente.setEscuela(cursor.getString(3));
            return docente;
        }else{
            return null;
        }
    }

    public String actualizar(Docente docente){

        if(verificarIntegridad(docente, 1)){

            String[] id = {docente.getCodigoDocente()};
            ContentValues cv = new ContentValues();
            cv.put("nombredocente", docente.getNombreDocente());
            cv.put("apellidodocente", docente.getApellidoDocente());
            cv.put("escuela", docente.getEscuela());
            db.update("docente", cv, "codigodocente = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con codigo " + docente.getCodigoDocente() + " no existe";
        }
    }

    public String eliminar(Docente docente){
        //Eliminacion en cascada
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(docente,2)) {
            contador+=db.delete("detalledocente", "codigodocente='"+docente.getCodigoDocente()+"'", null);
        }
        contador+=db.delete("docente", "codigodocente='"+docente.getCodigoDocente()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    public String eliminar2(Docente docente){
        //Eliminacion Restringida
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(docente,3)) {
            regAfectados="Error, existen registros asociados";
        }
        else
        {
            contador+=db.delete("alumno", "carnet='"+docente.getCodigoDocente()+"'", null);
            regAfectados+=contador;
        }


        return regAfectados;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BASE_DATOS = "proyecto1.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE docente(codigodocente VARCHAR(7) NOT NULL PRIMARY KEY, nombredocente VARCHAR(30),apellidodocente VARCHAR(30),escuela VARCHAR(30));");
                db.execSQL("CREATE TABLE detalledocente(codigo VARCHAR(6) NOT NULL PRIMARY KEY,codigogrupo VARCHAR(30),tiporol VARCHAR(1),nombrerol VARCHAR(30));");

            }catch(SQLException e){
                e.printStackTrace();

            }

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        }
    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar(){
        DBHelper.close();
    }

    public String insertar(Docente docente){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        if (verificarIntegridad(docente,1)) {
            regInsertados= "Error al Insertar docente, ya existe un docente con ese codigo";
       }
        else
        {
            ContentValues doc = new ContentValues();
            doc.put("codigodocente", docente.getCodigoDocente());
            doc.put("nombredocente", docente.getNombreDocente());
            doc.put("apellidodocente", docente.getApellidoDocente());
            doc.put("escuela", docente.getEscuela());
            contador=db.insert("docente", null, doc);
            regInsertados=regInsertados+contador;

        }




        return regInsertados;
    }



    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{

        switch(relacion){

            case 1:
            {
                //verificar que exista docente
                Docente docente2 = (Docente)dato;
                String[] id = {docente2.getCodigoDocente()};
                abrir();
                Cursor c2 = db.query("docente", null, "codigodocente = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Docente
                    return true;
                }
                return false;
            }
            case 2:
            {
                Docente docente = (Docente)dato;
                Cursor c=db.query(true, "detalledocente", new String[] {"codigo" }, "codigo='"+docente.getCodigoDocente()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }




            default:
                return false;


    }
    }

    public String llenarBD(){

        final String[] VDcodigo = {"LV10022","SC12037"};
        final String[] VDnombre = {"Waler","Cristian"};
        final String[] VDapellido = {"Lemus","Sosa"};
        final String[] VDescuela = {"Sistemas","Quimica"};

        abrir();
        db.execSQL("DELETE FROM docente");


        Docente docente = new Docente();
        for(int i=0;i<2;i++){
            docente.setCodigoDocente(VDcodigo[i]);
            docente.setNombreDocente(VDnombre[i]);
            docente.setApellidoDocente(VDapellido[i]);
            docente.setEscuela(VDescuela[i]);
            insertar(docente);
        }



        cerrar();
        return "Guardo Correctamente";
    }




}
