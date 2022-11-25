package org.example.RandomSerie;


public class Series {

    private int cod = 0;
    private String nombre= "";
    private int temporadas= 0;

    public Series(int cod, String nombre, int temporadas){
        this.cod= cod;
        this.nombre=nombre;
        this.temporadas=temporadas;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }




}
