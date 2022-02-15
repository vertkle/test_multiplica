package com.test.test_multi.models;

public class LetraCancionPOJO {
    private String lyrics;

    public LetraCancionPOJO(String letra) {
        this.lyrics = letra;
    }

    public String getLetra() {
        return lyrics;
    }


    @Override
    public String toString() {
        return "LetraCancionPOJO{" +
                "letra='" + lyrics + '\'' +
                '}';
    }
}
