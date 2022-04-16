package com.example.tfgprofes.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfgprofes.AddAlumnosActivity;
import com.example.tfgprofes.ClickAlumnoActivity;
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
        holder.etNombre.setText(listaAlumnos.get(position).getNombre());
        holder.etTelefono.setText(listaAlumnos.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        listaAlumnos.size();
        return listaAlumnos.size();
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder {

        TextView etNombre, etTelefono, etEmail, etTlfContacto, etPrecio, etTotal;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);

            etNombre = itemView.findViewById(R.id.etNombre);
            etTelefono = itemView.findViewById(R.id.etTelefono);
            etEmail = itemView.findViewById(R.id.etEmail);
            etTlfContacto = itemView.findViewById(R.id.etTlfContacto);
            etPrecio = itemView.findViewById(R.id.etPrecio);
            etTotal = itemView.findViewById(R.id.etTotal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ClickAlumnoActivity.class);
                    intent.putExtra("ID", listaAlumnos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }

}
