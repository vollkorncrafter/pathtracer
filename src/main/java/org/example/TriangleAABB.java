package org.example;
import org.joml.*;
import org.joml.Math;

public class TriangleAABB {
    public static AABBf computeAABBForTriangle(Vector3f v1, Vector3f v2, Vector3f v3) {
        float minX = Math.min(Math.min(v1.x, v2.x), v3.x);
        float minY = Math.min(Math.min(v1.y, v2.y), v3.y);
        float minZ = Math.min(Math.min(v1.z, v2.z), v3.z);
        float maxX = Math.max(Math.max(v1.x, v2.x), v3.x);
        float maxY = Math.max(Math.max(v1.y, v2.y), v3.y);
        float maxZ = Math.max(Math.max(v1.z, v2.z), v3.z);
        return new AABBf(minX, minY, minZ, maxX, maxY, maxZ);
    }
}
