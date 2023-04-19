package org.example;

import org.joml.Vector3f;

public class Noise {

    public static Vector3f noise(float mul){
        float fac = 0.5f * mul;
        return new Vector3f((float) (Math.random() * mul - fac), (float) (Math.random() * mul - fac), (float) (Math.random() * mul - fac));

    }

    public static Vector3f dir(Vector3f normal) {
        Vector3f n = normal;
        n.rotateX((float) ((Math.random() * Math.PI) - Math.PI/2));
        n.rotateY((float) ((Math.random() * Math.PI) - Math.PI/2));
        n.rotateZ((float) ((Math.random() * Math.PI) - Math.PI/2));
        n.normalize();
        return n;
    }

}
