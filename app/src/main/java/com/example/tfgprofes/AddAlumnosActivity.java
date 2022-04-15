package com.example.tfgprofes;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfgprofes.datosAlumnos.Alumnos;

import java.util.ArrayList;

public class AddAlumnosActivity extends AppCompatActivity {

    private EditText etNombre, etTelefono, etEmail, etTlfContacto, etPrecio, etTotal;
    private SQLiteDatabase bd;
    Alumnos alumno = null;
  //  public ArrayList<Alumnos> listaAlumnos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alumnos);

        etTelefono = findViewById(R.id.etTlf);
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etTlfContacto = findViewById(R.id.etTlfContacto);
        etPrecio = findViewById(R.id.etPrecio);
        etTotal = findViewById(R.id.etTotal);

    }


    public void altaAlumno(View v) {

        if (alumnoExistente()){
            Toast.makeText(this, "Este alumno ya existe", Toast.LENGTH_LONG).show();
        }else{
            try {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
                bd = admin.getWritableDatabase();

                String telefono = etTelefono.getText().toString();
                String nombre = etNombre.getText().toString();
                String email = etEmail.getText().toString();
                String telPersonaContacto = etTlfContacto.getText().toString();
                String precioHora = etPrecio.getText().toString();
                String total = etTotal.getText().toString();

                ContentValues registro = new ContentValues();

                registro.put("telefono", telefono);
                registro.put("nombre", nombre);
                registro.put("email", email);
                registro.put("telPersonaContacto", telPersonaContacto);
                registro.put("precioHora", precioHora);
                registro.put("total", total);

                bd.insert("alumno", null, registro);

                Toast.makeText(this, "Datos del alumno guardados", Toast.LENGTH_SHORT).show();


            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            } finally {
                LimpiarPantalla();
                bd.close();

            }
        }

    }

    private void LimpiarPantalla(){
        etTelefono.setText("");
        etNombre.setText("");
        etEmail.setText("");
        etTlfContacto.setText("");
        etPrecio.setText("");
        etTotal.setText("");

    }
////ESTA A LA NUEVA CLASE
//    public Alumnos verAlumnos(String tel) {
//        try {
//            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
//            SQLiteDatabase bd = admin.getWritableDatabase();
//
//            Cursor fila = bd.rawQuery("select * from alumno where telefono=" + tel + "", null);
//            // Cursor fila = bd.rawQuery("select * from alumno", null);
//
//            if (fila.moveToFirst()) {
//                alumno = new Alumnos();
//
//                alumno.setTelefono(fila.getString(0));
//                alumno.setNombre(fila.getString(1));
//                alumno.setEmail(fila.getString(2));
//                alumno.setTelPersonaContacto(fila.getString(3));
//                alumno.setPrecioHora(fila.getString(4));
//                alumno.setTotal(fila.getString(5));
//
//            }
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//        } finally {
//            bd.close();
//            return alumno;
//        }
//    }
//        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
//        SQLiteDatabase bd = admin.getWritableDatabase();
//
//
//        Alumnos alumno = null;
//
//        Cursor fila = bd.rawQuery("select * from alumno where telefono=" + tel + "", null);
//       // Cursor fila = bd.rawQuery("select * from alumno", null);
//
//        if (fila.moveToFirst()) {
//                alumno = new Alumnos();
//
//                alumno.setTelefono(fila.getString(0));
//                alumno.setNombre(fila.getString(1));
//                alumno.setEmail(fila.getString(2));
//                alumno.setTelPersonaContacto(fila.getString(3));
//                alumno.setPrecioHora(fila.getString(4));
//                alumno.setTotal(fila.getString(5));
//
//        }
//
//        bd.close();
//        return alumno;
//    }
//    public void verAlumno(tel) {
//        try {
//            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
//            SQLiteDatabase bd = admin.getWritableDatabase();
//
//            Cursor fila = bd.rawQuery("select nombre,email,personaContacto,telPersonaContacto,precioHora,total  from alumno where telefono=" + telefono + "", null);
//
//            if (fila.moveToFirst()) {
//                etTelefono.setText(fila.getString(0));
//                etNombre.setText(fila.getString(1));
//                etEmail.setText(fila.getString(2));
//                etTlfContacto.setText(fila.getString(3));
//                etPrecio.setText(fila.getString(4));
//                etTotal.setText(fila.getString(5));
//            } else {
//                Toast.makeText(this, "No existe el alumno", Toast.LENGTH_SHORT).show();
//                etTelefono.setText("");
//            }
//
//        }catch(Exception e){
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//        }finally {
//            bd.close();
//        }
//    }

//    public void consulta(View v) {
//        try {
//            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
//            SQLiteDatabase bd = admin.getWritableDatabase();
//
//            String telefono = etTelefono.getText().toString();
//
//            Cursor fila = bd.rawQuery("select nombre,email,personaContacto,telPersonaContacto,precioHora,total  from alumno where telefono=" + telefono + "", null);
//
//            if (fila.moveToFirst()) {
//                etNombre.setText(fila.getString(1));
//                etEmail.setText(fila.getString(2));
//                etTlfContacto.setText(fila.getString(3));
//                etPrecio.setText(fila.getString(4));
//                etTotal.setText(fila.getString(5));
//            } else {
//                Toast.makeText(this, "No existe el alumno", Toast.LENGTH_SHORT).show();
//                etTelefono.setText("");
//            }
//
//        }catch(Exception e){
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//        }finally {
//            bd.close();
//        }
//    }
//
//    public void baja(View v) {
//        try {
//            AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"administracion",null,1);
//            SQLiteDatabase bd=admin.getWritableDatabase();
//            String telefono=etTelefono.getText().toString();
//            int borrado=bd.delete("alumno", "telefono="+telefono+"",null);
//            if (borrado==1) {
//                Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
//            }
//        }catch (Exception e){
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//        }finally {
//            bd.close();
//            LimpiarPantalla();
//        }
//    }
//
//    public void modificacion(View v) {
//
//        try {
//            AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
//            SQLiteDatabase bd=admin.getWritableDatabase();
//
//            String telefono = etTelefono.getText().toString();
//            String nombre = etNombre.getText().toString();
//            String email = etEmail.getText().toString();
//            String telPersonaContacto = etTlfContacto.getText().toString();
//            String precioHora = etPrecio.getText().toString();
//            String total = etTotal.getText().toString();
//
//            ContentValues registro=new ContentValues();
//
//            registro.put("telefono", telefono);
//            registro.put("nombre", nombre);
//            registro.put("email", email);
//            registro.put("telPersonaContacto", telPersonaContacto);
//            registro.put("precioHora", precioHora);
//            registro.put("total", total);
//
//            int cant = bd.update("alumno", registro, "telefono="+ telefono, null);
//
//            if (cant==1) {
//                Toast.makeText(this, "Datos modificados correctamente", Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
//            }
//        }catch (Exception e){
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//        }finally {
//            bd.close();
//        }
//
//    }

    public boolean alumnoExistente(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String telefono = etTelefono.getText().toString();

        String Query = "Select * from alumno where telefono = " + telefono;
        Cursor cursor = bd.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }



}