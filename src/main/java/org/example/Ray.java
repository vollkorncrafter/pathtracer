package org.example;

import org.joml.Vector3f;

public class Ray {
    public Vector3f origin;
    public Vector3f  dir;

    public void setDir(Vector3f in){
        this.dir = in;
    }

    public Vector3f getDir(){
        return this.dir;
    }
    public Ray(Vector3f origin, Vector3f dir){
        this.origin = origin;
        this.dir = dir;
    }
}