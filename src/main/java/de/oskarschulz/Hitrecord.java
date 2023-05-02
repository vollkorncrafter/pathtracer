package de.oskarschulz;

import org.joml.Vector3f;

public class Hitrecord {
    public Vector3f normal;
    public Vector3f point;
    public double distance;

    public Material material;
    public Vector3f color;

    boolean Sky;
    public Hitrecord(Vector3f point, Vector3f normal, Vector3f cam, boolean isSky){
        this.point = point;
        this.normal = normal;
        this.Sky = isSky;
        if(point != null) {
            float dx = point.x - cam.x;
            float dy = point.y - cam.y;
            float dz = point.z - cam.z;
            this.distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        }
    }
}