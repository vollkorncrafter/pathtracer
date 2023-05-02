package de.oskarschulz;

import org.joml.Vector3f;

public class Materials {
    public static Material red = new Material(0,1,new Vector3f(0.4f,0.1f,0.1f),2);
    public static Material green = new Material(0,1,new Vector3f(0.1f,0.4f,0.1f),2);
    public static Material blue = new Material(0,1,new Vector3f(0,0,0.8f),2);

    public static Material grey = new Material(0,1,new Vector3f(0.2f,0.2f,0.2f),2);

    public static Material light = new Material(10,0,new Vector3f(100,100,100),1);

    public static Material sky = new Material(0,0,new Vector3f(0,0,0),0);

    public static Material ref = new Material(0,0,new Vector3f(0,0,0),3);
}
