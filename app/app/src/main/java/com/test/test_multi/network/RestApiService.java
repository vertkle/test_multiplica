package com.test.test_multi.network;

import com.test.test_multi.models.LetraCancionPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApiService {
    @GET("v1/{artista}/{cancion}")
    Call<LetraCancionPOJO>getLetraCancion(@Path("artista")String artista, @Path("cancion") String cancion);
}
