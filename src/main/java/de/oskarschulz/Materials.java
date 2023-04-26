package de.oskarschulz;

import org.joml.Vector3f;

public class Materials {
    public static Material red = new Material(0,1,new Vector3f(0.4f,0.1f,0.1f),"red");
    public static Material green = new Material(0,1,new Vector3f(0.1f,0.4f,0.1f),"green");
    public static Material blue = new Material(0,1,new Vector3f(0,0,0.8f),"blue");

    public static Material grey = new Material(0,1,new Vector3f(0.2f,0.2f,0.2f),"grey");

    public static Material light = new Material(10,0,new Vector3f(100,100,100),"light");

    public static Material sky = new Material(0,0,new Vector3f(0,0,0),"sky");
}
