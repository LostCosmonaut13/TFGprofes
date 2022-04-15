package com.example.tfgprofes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfgprofes.datosAlumnos.Alumnos;

public class ClickAlumnoActivity extends AppCompatActivity {

    private EditText etNombre, etTelefono, etEmail, etTlfContacto, etPrecio, etTotal;
    private SQLiteDatabase bd;
    Alumnos alumno;
    Button btnGuardar;

    String tele=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_alumno);

        btnGuardar = findViewById(R.id.btnGuardar);

        etTelefono = findViewById(R.id.etTlf);
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etTlfContacto = findViewById(R.id.etTlfContacto);
        etPrecio = findViewById(R.id.etPrecio);
        etTotal = findViewById(R.id.etTotal);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                tele = null;
            } else {
                tele = extras.getString("tel");
            }
        }else{
          //  tele = String.valueOf(savedInstanceState.getSerializable("tel"));
            tele = savedInstanceState.getString("tel");
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        alumno = verAlumno(tele);

        if(alumno != null){
            etTelefono.setText(alumno.getTelefono());
            etNombre.setText(alumno.getNombre());
            etEmail.setText(alumno.getEmail());
            etTlfContacto.setText(alumno.getTelPersonaContacto());
            etPrecio.setText(alumno.getPrecioHora());
            etTotal.setText(alumno.getTotal());

            //oculto boton Y! teclado (así sin calcar el boton EDITAR no se puede cambiar nada)
            btnGuardar.setVisibility(View.INVISIBLE);
            etTelefono.setInputType(InputType.TYPE_NULL);
            etNombre.setInputType(InputType.TYPE_NULL);
            etEmail.setInputType(InputType.TYPE_NULL);
            etTlfContacto.setInputType(InputType.TYPE_NULL);
            etPrecio.setInputType(InputType.TYPE_NULL);
            etTotal.setInputType(InputType.TYPE_NULL);

        }

    }

//    public Alumnos verAlumno() {
//        try {
//            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
//            SQLiteDatabase bd = admin.getWritableDatabase();
//
//            Cursor fila = bd.rawQuery("select nombre,email,personaContacto,telPersonaContacto,precioHora,total  from alumno where telefono=" + tel + "", null);
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
    public Alumnos verAlumno(String tel) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Alumnos alumno = null;

        Cursor fila = bd.rawQuery("select * from alumno where telefono=" + tel + "", null);
            // Cursor fila = bd.rawQuery("select * from alumno", null);

        if (fila.moveToFirst()) {
            alumno = new Alumnos();

            alumno.setTelefono(fila.getString(0));
            alumno.setNombre(fila.getString(1));
            alumno.setEmail(fila.getString(2));
            alumno.setTelPersonaContacto(fila.getString(3));
            alumno.setPrecioHora(fila.getString(4));
            alumno.setTotal(fila.getString(5));

        }

        bd.close();
        return alumno;

    }

}