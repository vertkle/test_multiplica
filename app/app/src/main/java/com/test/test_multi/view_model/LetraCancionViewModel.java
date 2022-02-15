package com.test.test_multi.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.test.test_multi.models.ArtistaCancionPOJO;
import com.test.test_multi.models.LetraCancionPOJO;
import com.test.test_multi.repositorio.LetraCancionRepositorio;

import java.util.List;

public class LetraCancionViewModel extends AndroidViewModel {
    private LetraCancionRepositorio letraCancionRepositorio;
    private LiveData<LetraCancionPOJO>letraLiveData;
    private LiveData<List<ArtistaCancionPOJO>>artistasLD;


    public LetraCancionViewModel(@NonNull Application application) {
        super(application);
        letraCancionRepositorio = new LetraCancionRepositorio();
        this.letraLiveData = letraCancionRepositorio.getLetraLD();
        this.artistasLD = letraCancionRepositorio.getArtistaLD();
    }
    public void buscar_cancion(String s1,String s2){
        letraCancionRepositorio.getLetraCancion(s1,s2);
    }

    public void artistasCanciones(String artistaCancion){
        letraCancionRepositorio.mostrarArtistaCancion(artistaCancion);
    }





    public LiveData<LetraCancionPOJO> getLetraLiveData(){
        return letraLiveData;
    }
    public LiveData<List<ArtistaCancionPOJO>>getArtistasLD(){
        return artistasLD;
    }
}
