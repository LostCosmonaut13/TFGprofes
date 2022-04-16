package com.example.tfgprofes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfgprofes.datosAlumnos.Alumnos;

public class ClickAlumnoActivity extends AppCompatActivity {

    private EditText etNombre, etTelefono, etEmail, etTlfContacto, etPrecio, etTotal;
    private SQLiteDatabase bd;
    Alumnos alumno;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_alumno);

        etTelefono = findViewById(R.id.etTelefono);
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etTlfContacto = findViewById(R.id.etTlfContacto);
        etPrecio = findViewById(R.id.etPrecio);
        etTotal = findViewById(R.id.etTotal);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        }else{
            id = (int)savedInstanceState.getSerializable("ID");
        }

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        alumno = verAlumno(id);

        if(alumno != null){
            etTelefono.setText(alumno.getTelefono());
            etNombre.setText(alumno.getNombre());
            etEmail.setText(alumno.getEmail());
            etTlfContacto.setText(alumno.getTelPersonaContacto());
            etPrecio.setText(alumno.getPrecioHora());
            etTotal.setText(alumno.getTotal());

        }

    }

    public Alumnos verAlumno(int id) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Alumnos alumno = null;

        Cursor fila = bd.rawQuery("select * from alumno where id=" + id + "", null);


        if (fila.moveToFirst()) {
            alumno = new Alumnos();

            alumno.setId(fila.getInt(0));
            alumno.setTelefono(fila.getString(1));
            alumno.setNombre(fila.getString(2));
            alumno.setEmail(fila.getString(3));
            alumno.setTelPersonaContacto(fila.getString(4));
            alumno.setPrecioHora(fila.getString(5));
            alumno.setTotal(fila.getString(6));

        }

        bd.close();
        return alumno;

    }

    public void modificarAlumno(View v) {

        try {
            AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd=admin.getWritableDatabase();

            String telefono = etTelefono.getText().toString();
            String nombre = etNombre.getText().toString();
            String email = etEmail.getText().toString();
            String telPersonaContacto = etTlfContacto.getText().toString();
            String precioHora = etPrecio.getText().toString();
            String total = etTotal.getText().toString();

            ContentValues registro=new ContentValues();

            registro.put("telefono", telefono);
            registro.put("nombre", nombre);
            registro.put("email", email);
            registro.put("telPersonaContacto", telPersonaContacto);
            registro.put("precioHora", precioHora);
            registro.put("total", total);

            int cant = bd.update("alumno", registro, "id="+ id, null);

            if (cant==1) {
                Toast.makeText(this, "Datos modificados correctamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ClickAlumnoActivity.this, MainActivity.class));
                finish();
            }else {
                Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }finally {
            bd.close();
            LimpiarPantalla();
        }

    }

        public void borrarAlumno(View v) {
        try {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String telefono = etTelefono.getText().toString();

            int borrado=bd.delete("alumno", "id="+ id +"",null);
            if (borrado==1) {
                Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ClickAlumnoActivity.this, MainActivity.class));
                finish();
            }
            else {
                Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }finally {
            bd.close();
            LimpiarPantalla();
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


}