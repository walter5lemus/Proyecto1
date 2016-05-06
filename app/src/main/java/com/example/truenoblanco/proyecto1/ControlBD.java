package com.example.truenoblanco.proyecto1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TruenoBlanco on 5/5/2016.
 */
public class ControlBD {

    private static final String[]camposDocente = new String [] {"codigoDocente","nombreDocente","ApellidoDocente","escuela"};
    private static final String[]camposDetalleDocente = new String [] {"codigoDocente","codigoGrupo","ciclo","notafinal"};


    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

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
                db.execSQL("CREATE TABLE detalledocente(codigodocente VARCHAR(6) NOT NULL PRIMARY KEY,codigogrupo VARCHAR(30),tiporol VARCHAR(1),nombrerol VARCHAR(30));");
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

        //if (verificarIntegridad(docente,5)) {
          //  regInsertados= "Error al Insertar docente, Registro Duplicado(PK). Verificar inserción";
        //}
        //else
        //{
            ContentValues doc = new ContentValues();
            doc.put("codigodocente", docente.getCodigoDocente());
            doc.put("nombredocente", docente.getNombreDocente());
            doc.put("apellidodocente", docente.getApellidoDocente());
            doc.put("escuela", docente.getEscuela());
            contador=db.insert("docente", null, doc);
            regInsertados=regInsertados+contador;

//        }




        return regInsertados;
    }

/*    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{

        switch(relacion){

            case 1:
            {
                //verificar que al insertar nota exista carnet del alumno y el codigo de materia
                Nota nota = (Nota)dato;
                String[] id1 = {nota.getCarnet()};
                String[] id2 = {nota.getCodmateria()};
                //abrir();
                Cursor cursor1 = db.query("alumno", null, "carnet = ?", id1, null, null, null);
                Cursor cursor2 = db.query("materia", null, "codmateria = ?", id2, null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

            case 2:
            {
                //verificar que al modificar nota exista carnet del alumno, el codigo de materia y el ciclo
                Nota nota1 = (Nota)dato;
                String[] ids = {nota1.getCarnet(), nota1.getCodmateria(), nota1.getCiclo()};
                abrir();
                Cursor c = db.query("nota", null, "carnet = ? AND codmateria = ? AND ciclo = ?", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

            case 3:
            {
                Alumno alumno = (Alumno)dato;
                Cursor c=db.query(true, "nota", new String[] {
                        "carnet" }, "carnet='"+alumno.getCarnet()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }

            case 4:
            {
                Materia materia = (Materia)dato;
                Cursor cmat=db.query(true, "nota", new String[] {
                        "codmateria" }, "codmateria='"+materia.getCodmateria()+"'",null, null, null, null, null);
                if(cmat.moveToFirst())
                    return true;
                else
                    return false;
            }

            case 5:
            {
                //verificar que exista alumno
                Alumno alumno2 = (Alumno)dato;
                String[] id = {alumno2.getCarnet()};
                abrir();
                Cursor c2 = db.query("alumno", null, "carnet = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Alumno
                    return true;
                }
                return false;
            }

            case 6:
            {
                //verificar que exista Materia
                Materia materia2 = (Materia)dato;
                String[] idm = {materia2.getCodmateria()};
                abrir();
                Cursor cm = db.query("materia", null, "codmateria = ?", idm, null, null, null);
                if(cm.moveToFirst()){
                    //Se encontro Materia
                    return true;
                }
                return false;
            }

            default:
                return false;

        }


    }*/





}
