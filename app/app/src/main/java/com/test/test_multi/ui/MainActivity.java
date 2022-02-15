package com.test.test_multi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.test.test_multi.R;
import com.test.test_multi.databinding.ActivityMainBinding;
import com.test.test_multi.view_model.LetraCancionViewModel;

import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    LetraCancionViewModel viewModel;
    Context ctx;
    ActivityMainBinding binding;
    String nomCancion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        buscarCanciones();
        seleccionarLetra();
        mostrar_artistas();
        mostrarLetra();








    }

    private void init(){
        ctx = this;
        viewModel = ViewModelProviders.of(this).get(LetraCancionViewModel.class);


    }
    private void buscarCanciones(){
        binding.txtArtistas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.artistasCanciones(editable.toString());
            }
        });
    }
    private void seleccionarLetra(){
        binding.txtArtistas.setOnItemClickListener((adapterView, view, i, l) -> {
            binding.txtArtistas.setText("");
            String item[] = adapterView.getItemAtPosition(i).toString().split("-");
            nomCancion = adapterView.getItemAtPosition(i).toString();
            viewModel.buscar_cancion(item[0].trim(),item[1].trim());

        });
    }
    

    private void mostrar_artistas(){
        viewModel.getArtistasLD().observe(MainActivity.this,artistaCancionPOJOS -> {
            ArrayAdapter arra = new ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item,artistaCancionPOJOS);
            binding.txtArtistas.setAdapter(arra);
        });
    }
    private void mostrarLetra(){
        viewModel.getLetraLiveData().observe(this,letraCancionPOJO -> {
            if(letraCancionPOJO != null){
                binding.tvLetraCancion.setText(letraCancionPOJO.getLetra());
                binding.tvNombreCancion.setText(nomCancion);
            }else{
                binding.tvLetraCancion.setText(R.string.txt_error);
                binding.tvNombreCancion.setText("");
            }
        });
    }

}