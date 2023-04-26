package de.oskarschulz;

import org.joml.Vector3f;

public class Hitrecord {
    public Vector3f normal;
    public Vector3f point;
    public double distance;

    public Material material;
    public Vector3f color;

    public Vector3f radiance;
    public Vector3f lightDirection;
    public Vector3f viewDirection;

    public String id;
    boolean Sky;
    public Hitrecord(Vector3f point, Vector3f normal, Vector3f cam, boolean isSky, String id){
        this.point = point;
        this.normal = normal;
        this.Sky = isSky;
        this.id = id;
        float dx = point.x - cam.x;
        float dy = point.y - cam.y;
        float dz = point.z - cam.z;
        this.distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        radiance = new Vector3f(1,1,1);
    }
}