package de.oskarschulz;

import org.joml.Vector3f;

public class Material {

    Vector3f color;
    public float emission;
    public float roughness;

    public int id;

    public Material(float emission,float roughness,Vector3f color, int id){
        this.color = color;
        this.emission = emission;
        this.roughness = roughness;
        this.id = id;
    }

}
