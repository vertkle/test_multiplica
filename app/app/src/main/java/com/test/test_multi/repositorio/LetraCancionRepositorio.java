package com.test.test_multi.repositorio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.test.test_multi.models.ArtistaCancionPOJO;
import com.test.test_multi.models.LetraCancionPOJO;
import com.test.test_multi.network.RestApiService;
import com.test.test_multi.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LetraCancionRepositorio {
    private RestApiService apiRequest;
    private MutableLiveData<LetraCancionPOJO> letraCancionLD;
    private MutableLiveData<List<ArtistaCancionPOJO>>artistaLD;
    List<ArtistaCancionPOJO> lista;

    public LetraCancionRepositorio() {
        apiRequest = RetrofitInstance.getInstance().create(RestApiService.class);
        letraCancionLD = new MutableLiveData<>();
        artistaLD = new MutableLiveData<>();
        lista = new ArrayList<>();
        lista.add(new ArtistaCancionPOJO("Elvis Crespo","Suavemente"));lista.add(new ArtistaCancionPOJO("Aventura","Obsesion"));
        lista.add(new ArtistaCancionPOJO("Bad Bunny","Yonaguni"));lista.add(new ArtistaCancionPOJO("Rosalia","Malamente"));
        lista.add(new ArtistaCancionPOJO("Juan Gabriel","Querida"));lista.add(new ArtistaCancionPOJO("Ricardo Arjona","Mujeres"));
        lista.add(new ArtistaCancionPOJO("Wisin","Vacaciones"));lista.add(new ArtistaCancionPOJO("Reik","Sabes"));
        lista.add(new ArtistaCancionPOJO("Roberto Carlos","Amigo"));lista.add(new ArtistaCancionPOJO("luis miguel","no se tu"));
        lista.add(new ArtistaCancionPOJO("Daddy Yankee","Dura"));lista.add(new ArtistaCancionPOJO("Luis Fonsi","Despacito"));
        lista.add(new ArtistaCancionPOJO("Enrique Iglesias","Bailando"));lista.add(new ArtistaCancionPOJO("Daddy Yankee","Gasolina"));
    }

    public LiveData<LetraCancionPOJO>getLetraCancion(String artista,String cancion){
        apiRequest.getLetraCancion(artista,cancion).enqueue(new Callback<LetraCancionPOJO>() {
            @Override
            public void onResponse(Call<LetraCancionPOJO> call, Response<LetraCancionPOJO> response) {
                if (response.body() != null) {
                    letraCancionLD.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LetraCancionPOJO> call, Throwable t) {
                letraCancionLD.postValue(null);
            }
        });

        return letraCancionLD;
    }
    public LiveData<LetraCancionPOJO>getLetraLD(){
        return letraCancionLD;
    }


    public LiveData<List<ArtistaCancionPOJO>>mostrarArtistaCancion(String artistaCancion){
       List<ArtistaCancionPOJO>lstmp = new ArrayList<>();
        for(ArtistaCancionPOJO ar: lista){
            if(ar.toString().toLowerCase().contains(artistaCancion.toLowerCase())){
                lstmp.add(ar);
            }
        }
        artistaLD.postValue(lstmp);
        return artistaLD;
    }
    public LiveData<List<ArtistaCancionPOJO>>getArtistaLD(){
        return artistaLD;
    }
}
