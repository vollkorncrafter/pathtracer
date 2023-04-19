package org.example;

import org.joml.Vector3f;

import java.util.ArrayList;

public class Camera {

    public Hittables verts = new Hittables();

    public static Vector3f pointOnImagePlane(Vector3f lower_left, Vector3f horizontal, Vector3f vertical, Vector3f origin, float u, float v) {
        Vector3f ll = new Vector3f(lower_left);
        Vector3f vh = new Vector3f(horizontal);
        Vector3f vv = new Vector3f(vertical);
        vh.mul(u);
        ll.add(vh);
        vv.mul(v);
        ll.add(vv);
        ll.sub(origin);
        return ll;
    }

    public static Vector3f lowerLeftCorner(Vector3f origin, Vector3f horizontal, Vector3f vertical, float focal_length) {
        Vector3f o = new Vector3f(origin);
        Vector3f h = new Vector3f(horizontal).div(2);
        Vector3f v = new Vector3f(vertical).div(2);
        o.sub(h).sub(v).sub(0f, 0f, focal_length);
        return o;
    }



    public Vector3f renderPixel(Ray r, int n) {
        Vector3f col = new Vector3f(0f, 0f, 0f);
        for (int i = 0; i < n; i++) {
            Hitrecord hit = verts.hitAll(r);

            if (hit.Sky) {
                col = new Vector3f(0,1,0);
            }else{


                //System.out.println("true");
                col = hit.color;
            }
        }
        col.mul(255);
        return col;
    }


}
