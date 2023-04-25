package org.example;

import org.joml.Vector3f;

import java.util.ArrayList;

public class Hittables {

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
        sky.material = Materials.sky;
        return sky;
    }
}
