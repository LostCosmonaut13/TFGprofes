package com.example.tfgprofes.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfgprofes.R;
import com.example.tfgprofes.datosAlumnos.Alumnos;

import java.util.ArrayList;

public class ListaAlumnosAdapter extends RecyclerView.Adapter<ListaAlumnosAdapter.AlumnoViewHolder> {

    ArrayList<Alumnos> listaAlumnos;

    public ListaAlumnosAdapter(ArrayList<Alumnos> listaAlumnos){
        this.listaAlumnos = listaAlumnos;
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_alumnos,null, false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        holder.viewNombre.setText(listaAlumnos.get(position).getNombre());
        holder.viewTelefono.setText(listaAlumnos.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        listaAlumnos.size();
        return listaAlumnos.size();
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);

        }
    }
}
