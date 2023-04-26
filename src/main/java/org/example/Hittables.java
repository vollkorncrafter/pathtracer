package org.example;

import org.joml.Vector3f;

import java.util.ArrayList;

public class Hittables {

    public Vector3f getSkyboxColor(Vector3f rayDirection) {
        Vector3f topColor = new Vector3f(0.5f, 0.5f, 1);   // light blue
        Vector3f bottomColor = new Vector3f(0.3f, 0.3f, 0.3f);  // white

        float t = (rayDirection.y + 1.0f) * 0.5f;  // map y-coordinate to [0, 1]
        Vector3f color = bottomColor.lerp(topColor,t);
        return color;
    }

    public ArrayList<Triangle> tris = new ArrayList<>();

    public Hitrecord hitAll(Ray r) {
        ArrayList<Hitrecord> hits = new ArrayList<>();
        for (int i = 0; i < tris.size(); i++) {
            Triangle c = tris.get(i);
            hits.add(intersection.rayTriangleIntersection(r, c));
        }

        for (int i = 0; i < hits.size(); i++) {
            if (hits.get(i) == null) {
                hits.remove(i);
                i--;
            }

        }

        if (hits.size() > 0) {
            Hitrecord out = hits.get(0);
            for (int i = 1; i < hits.size(); i++) {
                if (hits.get(i).distance < out.distance) {
                    out = hits.get(i);
                }
            }

            return out;
        }
        Hitrecord sky = new Hitrecord(new Vector3f(),new Vector3f(),new Vector3f(),true,null);
        sky.color = getSkyboxColor(r.dir);
        sky.material = Materials.sky;
        return sky;
    }
}
