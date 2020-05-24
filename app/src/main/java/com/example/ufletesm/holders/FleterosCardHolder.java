package com.example.ufletesm.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ufletesm.R;

public class FleterosCardHolder extends  RecyclerView.ViewHolder{

    public ImageView imagenFletero;
    public TextView nombreFletero;
    public TextView numeroFletero;

    public FleterosCardHolder(@NonNull View itemView) {
        super(itemView);
        imagenFletero = itemView.findViewById(R.id.imageViewVehiculoFletero_busqueda);
        nombreFletero = itemView.findViewById(R.id.txtNombreFletero_ListaBusqueda);
        numeroFletero = itemView.findViewById(R.id.txtTelefono_ListaBusqueda);
    }
}
