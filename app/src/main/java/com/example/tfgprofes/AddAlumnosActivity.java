package com.example.tfgprofes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfgprofes.datosAlumnos.Alumnos;

import java.util.ArrayList;

public class AddAlumnosActivity extends AppCompatActivity {

    private EditText etNombre, etTelefono, etEmail, etTlfContacto, etPrecio, etTotal;
    private SQLiteDatabase bd;
    Alumnos alumno = null;
    long id = 0;
    Button bHorario;


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

        bHorario = findViewById(R.id.btHorario);

        bHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!etNombre.getText().toString().isEmpty()){

                    Intent intent = new Intent(Intent.ACTION_INSERT); //para insertar un evento en GCalendar
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, etNombre.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "false");

                 //   if(intent.resolveActivity(getPackageManager()) != null){ //si el calendario puede hacer esto
                    startActivity(intent);
                  //  }else{
                   //     Toast.makeText(AddAlumnosActivity.this, "No se ha podido añadir el horario", Toast.LENGTH_SHORT).show();
                  //  }

                }else{
                    Toast.makeText(AddAlumnosActivity.this, "Debes completar el campo Nombre", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public long altaAlumno(View v) {

        id = 0;

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

                id = bd.insert("alumno", null, registro);

                Toast.makeText(this, "Datos del alumno guardados", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddAlumnosActivity.this, MainActivity.class));
                finish();


            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            } finally {
                LimpiarPantalla();
                bd.close();

            }
        }

        return id;

    }

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


    private void LimpiarPantalla(){
        etTelefono.setText("");
        etNombre.setText("");
        etEmail.setText("");
        etTlfContacto.setText("");
        etPrecio.setText("");
        etTotal.setText("");

    }

}