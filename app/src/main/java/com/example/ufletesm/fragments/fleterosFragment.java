package com.example.ufletesm.fragments;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ufletesm.R;
import com.example.ufletesm.holders.FleterosCardHolder;
import com.example.ufletesm.models.Fleteros;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.Query;
import com.google.firestore.v1.StructuredQuery;

import static com.google.firebase.firestore.FirebaseFirestore.getInstance;

public class fleterosFragment extends Fragment {

    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter<Fleteros, FleterosCardHolder> adapter;
    private FirestoreRecyclerOptions<Fleteros> options;
    private Button filterButton;
    private EditText searchBox;
    Query query;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_fleteros, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = rootView.findViewById(R.id.fleterosRV);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        final String[] order = getResources().getStringArray(R.array.order);
        filterButton = rootView.findViewById(R.id.filterButton);
        searchBox = rootView.findViewById(R.id.searchBox);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                materialAlertDialogBuilder.setTitle(getString(R.string.modal_title));
                materialAlertDialogBuilder.setItems(order, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeOrder(order[which]);
                    }
                }).show();


            }
        });

        getData();
    }

    private void getData() {
        query = getInstance()
                .collection("Fletero")
                .orderBy("correo");

        options = new FirestoreRecyclerOptions.Builder<Fleteros>()
                .setQuery(query, Fleteros.class)
                .build();
        attachRecyclerView();
        adapter.notifyDataSetChanged();
    }

    private void attachRecyclerView() {
        adapter = new FirestoreRecyclerAdapter<Fleteros, FleterosCardHolder>(options) {


            @NonNull
            @Override
            public FleterosCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fleteros_card, parent, false);
                return new FleterosCardHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FleterosCardHolder holder, int position, @NonNull Fleteros model) {
                Glide.with(getContext())
                        .load(model.getPathFoto_v())
                        .centerCrop()
                        .placeholder(R.drawable.ic_vehiculo_na)
                        .into(holder.imagenFletero);

                holder.nombreFletero.setText(String.format("%s %s", model.getNombre(), model.getApellidop()));
                holder.numeroFletero.setText(model.getTelefono());
            }
        };
        recyclerView.setAdapter(adapter);
    }

    private void changeOrder(String s) {
        adapter.stopListening();
        if (s.equals("Ascendente")){
            query = getInstance()
                    .collection("Fletero")
                    .orderBy("nombre", Query.Direction.ASCENDING);
        }
        else if (s.equals("Descendente")){
            query = getInstance()
                    .collection("Fletero")
                    .orderBy("nombre", Query.Direction.DESCENDING);
        }

        else if(s.equals("Neutral")){
            query = getInstance().collection("Fletero").orderBy("correo");
        }

        final String[] order = getResources().getStringArray(R.array.order);

        filterButton = rootView.findViewById(R.id.filterButton);
        searchBox = rootView.findViewById(R.id.searchBox);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(getActivity());
                materialAlertDialogBuilder.setTitle(getString(R.string.modal_title));
                materialAlertDialogBuilder.setItems(order, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        changeOrder(order[which]);
                    }
                }).show();


            }
        });

        options = new FirestoreRecyclerOptions.Builder<Fleteros>()
                .setQuery(query, Fleteros.class)
                .build();
        attachRecyclerView();
        adapter.startListening();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
