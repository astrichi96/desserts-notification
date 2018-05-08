package com.company.haaru.postresnotificacion.Controlador;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.haaru.postresnotificacion.MainActivity;
import com.company.haaru.postresnotificacion.R;

import java.util.List;

/**
 * Created by NoridaVanegas on 20/11/2016.
 */

public class PostreAdapter extends RecyclerView.Adapter<PostreAdapter.postreViewHolder>{

    List<Postre> postres;

    public PostreAdapter(List<Postre> postres) {
        this.postres = postres;
    }

    @Override
    public postreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new postreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final postreViewHolder holder, int position) {
        final Postre p = postres.get(position);
        holder.titulo.setText(p.getTitulo());
        holder.descripcion.setText(p.getDescripcion());
        holder.imgFoto.setImageResource(p.getFoto());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aqui tienes el onclic ya haz tu magia v:
                Toast.makeText(MainActivity.con,p.getDescripcion(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return postres.size();

    }

    public static class postreViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView descripcion;
        ImageView imgFoto;
        CardView cv;

        public postreViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.textNombre);
            descripcion = (TextView) itemView.findViewById(R.id.textDescripcion);
            imgFoto = (ImageView) itemView.findViewById((R.id.imgImagen));
            cv = (CardView) itemView.findViewById(R.id.cvPostres);
        }
    }
}
