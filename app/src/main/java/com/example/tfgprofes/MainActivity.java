package com.example.tfgprofes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tfgprofes.adaptadores.ListaAlumnosAdapter;
import com.example.tfgprofes.datosAlumnos.Alumnos;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private SQLiteDatabase bd;
    public ArrayList<Alumnos> listaAlumnos;
    RecyclerView listadoAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        listadoAlumnos = findViewById(R.id.rvListaAlumnos);
        listadoAlumnos.setLayoutManager(new LinearLayoutManager(this));

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);

        ListaAlumnosAdapter adapter = new ListaAlumnosAdapter(mostrarAlumnos());
        listadoAlumnos.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusuperior, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.agregar:
                Intent intent = new Intent(MainActivity.this, AddAlumnosActivity.class);
                startActivity(intent);
                return true;

            case R.id.cerrar:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
                Toast.makeText(this, "Bye bye!", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ArrayList<Alumnos> mostrarAlumnos() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ArrayList<Alumnos> listaAlumnos = new ArrayList<>();
        Alumnos alumno = null;

        Cursor fila = bd.rawQuery("select telefono,nombre from alumno", null);

        if (fila.moveToFirst()) {
            do {
                alumno = new Alumnos();

                alumno.setTelefono(fila.getString(0));
                alumno.setNombre(fila.getString(1));

                listaAlumnos.add(alumno);
            } while (fila.moveToNext());
        }

        bd.close();
        return listaAlumnos;

    }

}