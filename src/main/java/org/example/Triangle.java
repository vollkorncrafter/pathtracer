package org.example;

import org.joml.RayAabIntersection;
import org.joml.Rectanglef;
import org.joml.Vector3f;

import java.util.Vector;

import org.joml.Rectanglef.*;
import org.joml.RayAabIntersection.*;

public class Triangle {
    public Vector3f v1;
    public Vector3f v2;
    public Vector3f v3;

    public Vector3f normal;
    public String id;
    public String mat;
    
    public Triangle(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f normal, String material){
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.normal=normal;
        this.mat = material;

        id = v1.toString() + v2.toString() + v3.toString();
    }


}
