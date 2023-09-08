package org.example;

import java.util.List;

public class RepositorioVertices {
    private List<Vertice> vertices;
    private RepositorioVertices instance;

    private RepositorioVertices(){
        vertices = new ArrayList<>();
    }
    public static instance getInstance(){
        if( intance == null){
            instance = new RepositorioVertices();
        }else{
            return instance;
        }
    }
}
