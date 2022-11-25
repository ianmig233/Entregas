package org.example.RandomSerie.src.com.ian.EJ4;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Seriess {

    private ArrayList<Series> serie;

    public Seriess(){
        serie = new ArrayList<>();
    }

    public void addSeries(Series s){
        serie.add(s);

    }

    public void muestraSeries(){
        for (Series s: serie) {
            System.out.println(s);

        }
    }
}
