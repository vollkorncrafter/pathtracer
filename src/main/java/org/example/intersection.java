package org.example;

import org.joml.*;
import org.joml.Math;
import org.joml.Vector3f;

import static org.joml.Intersectionf.intersectLineSegmentTriangle;

public class intersection {



    public static boolean intersectsAABB(Ray ray, Triangle tri) {
        Vector3f direction = ray.dir;
        Vector3f origin = ray.origin;
        Vector3f inv_direction = new Vector3f(1f / direction.x, 1f / direction.y, 1f / direction.z);

        float tmin_x = (tri.aab.minX - origin.x) * inv_direction.x;
        float tmax_x = (tri.aab.maxX - origin.x) * inv_direction.x;
        float tmin_y = (tri.aab.minY - origin.y) * inv_direction.y;
        float tmax_y = (tri.aab.maxY - origin.y) * inv_direction.y;

        float tmin = Math.max(Math.min(tmin_x, tmax_x), Math.min(tmin_y, tmax_y));
        float tmax = Math.min(Math.max(tmin_x, tmax_x), Math.max(tmin_y, tmax_y));

        return tmax >= tmin;
    }




    public static Hitrecord rayTriangleIntersection(Ray r,Triangle c) {




    //System.out.println(intersectsAABB(r,c));









        //if(intersectsAABB(r,c)) {



            Vector3f v0 = c.v1;
            Vector3f v1 = c.v2;
            Vector3f v2 = c.v3;
            Vector3f origin = r.origin;
            Vector3f direction = r.dir;
            Vector3f edge1 = new Vector3f(v1).sub(v0);
            Vector3f edge2 = new Vector3f(v2).sub(v0);
            Vector3f pvec = new Vector3f(direction).cross(edge2);
            float det = edge1.dot(pvec);

            if (det == 0) {
                return null;
            }


            float invDet = 1.0f / det;
            Vector3f tvec = new Vector3f(origin).sub(v0);
            float u = tvec.dot(pvec) * invDet;

            if (u < 0 || u > 1) {
                return null;
            }

            Vector3f qvec = new Vector3f(tvec).cross(edge1);
            float v = direction.dot(qvec) * invDet;

            if (v < 0 || u + v > 1) {
                return null;
            }

            float t = edge2.dot(qvec) * invDet;

            if (t <= 0) {
                return null;
            }

            Vector3f intersectionPoint = new Vector3f(direction).mul(t).add(origin);


            Hitrecord out = new Hitrecord(intersectionPoint, c.normal, r.origin, false, c.id);
            out.point = intersectionPoint;
            out.normal = c.normal;

            if (c.mat.equals("red")) {
                out.color = new Vector3f(0.4f, 0.1f, 0.1f);
                out.radiance = new Vector3f(0.8f, 0.8f, 0.8f);

                out.material = Materials.red;


            } else if (c.mat.equals("green")) {
                out.color = new Vector3f(0.1f, 0.4f, 0.1f);
                out.radiance = new Vector3f(0.8f, 0.8f, 0.8f);

                out.material = Materials.green;
            } else if (c.mat.equals("grey")) {
                out.color = new Vector3f(0.4f, 0.4f, 0.4f);
                out.radiance = new Vector3f(0.8f, 0.8f, 0.8f);

                out.material = Materials.grey;
            } else if (c.mat.equals("light")) {
                out.color = new Vector3f(
                        50, 50, 50);
                out.radiance = new Vector3f(10f, 10f, 10f);

                out.material = Materials.light;
            }

            return out;
        }



}
