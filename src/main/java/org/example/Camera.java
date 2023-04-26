package org.example;

import org.apache.commons.lang3.ArrayUtils;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Objects;

public class Camera {

    public static Hittables verts = new Hittables();
    public static Hitrecord[] hitArray;



    public static Ray randomizeNormal(Vector3f normal, int maxRotationAngle, Vector3f hitPoint, float offset) {

            Vector3f randomizedNormal = Noise.dir(normal);

            Vector3f offsetHitPoint = new Vector3f(hitPoint).add(new Vector3f(randomizedNormal).mul(offset));

            //System.out.println(randomizedNormal);

            return new Ray(offsetHitPoint,randomizedNormal);
        }


    public void bounce(int max, Ray r) {

        for(int i = 0; i < max; i++){
            Hitrecord h = verts.hitAll(r);
            hitArray[i] = h;
            if(Objects.equals(h.material.name, "light") || Objects.equals(h.material.name, "sky")){
                break;
            }else {
                r = randomizeNormal(h.normal, 90, h.point, 0.001f);
            }

        }
    }






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

    int bounces = 5;

    public Vector3f renderPixel(Ray r, int n) {


        hitArray = new Hitrecord[bounces];


        Vector3f col = new Vector3f(0, 0, 0);
        Vector3f color = null;
        for (int i = 0; i < n; i++) {
            Hitrecord hit = verts.hitAll(r);
            col = hit.color;

            if (hit.Sky) {
                col = hit.color;
            } else if (Objects.equals(hit.material.name, "light")) {
                col = new Vector3f(1, 1, 1);
            } else {
                Ray secondaryRay = randomizeNormal(hit.normal, 90, hit.point, 0.001f);

                bounce(bounces, secondaryRay);
                color = hitArray[0].color;
                for (int x = 1; x < hitArray.length-1; x++) {
                    if(hitArray[x] != null) {
                        color.mul(hitArray[x].color);
                        //System.out.println(hitArray[x].color);
                        if (Objects.equals(hitArray[x].material.name, "light") || Objects.equals(hitArray[x].material.name, "sky")) {
                            break;
                        }
                    }
                }
            }

            }


        if (col != null) {
            if (color != null){
                col.mul(color);
            }
            col.mul(255);
            return col;
        }
        return new Vector3f(0, 0, 0);
    }


}
