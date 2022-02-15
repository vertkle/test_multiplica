package com.test.test_multi.models;

public class ArtistaCancionPOJO {
    private String artista;
    private String cancion;

    public ArtistaCancionPOJO(String artista, String cancion) {
        this.artista = artista;
        this.cancion = cancion;
    }

    public String getArtista() {
        return artista;
    }

    public String getCancion() {
        return cancion;
    }

    @Override
    public String toString() {
        return artista+" - "+cancion;
    }
}
