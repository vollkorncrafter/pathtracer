package org.example;

import org.joml.Vector3f;

public class Material {

    Vector3f color;
    public float emission;
    public float roughness;

    public String name;

    public Material(float emission,float roughness,Vector3f color, String name){
        this.color = color;
        this.emission = emission;
        this.roughness = roughness;
        this.name = name;
    }

}
