package de.oskarschulz;

import org.joml.Vector3f;

public class Hittables {

    public Vector3f getSkyboxColor(Vector3f rayDirection) {
        Vector3f topColor = new Vector3f(0.5f, 0.5f, 1);   // light blue
        Vector3f bottomColor = new Vector3f(0.3f, 0.3f, 0.3f);  // white

        float t = (rayDirection.y + 1.0f) * 0.5f;  // map y-coordinate to [0, 1]
        Vector3f color = bottomColor.lerp(topColor, t);
        return color;
    }

    public Triangle[] tris;

    public Hitrecord hitAll(Ray r) {
        Hitrecord[] hits = new Hitrecord[tris.length];
        int hitCount = 0;
        for (int i = 0; i < tris.length; i++) {
            Triangle c = tris[i];
            Hitrecord hr = intersection.rayTriangleIntersection(r, c);
            if (hr != null) {
                hits[hitCount++] = hr;
            }
        }

        if (hitCount > 0) {
            Hitrecord out = hits[0];
            for (int i = 1; i < hitCount; i++) {
                if (hits[i].distance < out.distance) {
                    out = hits[i];
                }
            }

            return out;
        }

        Hitrecord sky = new Hitrecord(null, null, null, true);
        sky.color = getSkyboxColor(r.dir);
        sky.material = Materials.sky;
        return sky;
    }
}
