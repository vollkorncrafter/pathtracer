package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.joml.Vector3f;

public class OBJLoader {
    public static Triangle[] loadTrianglesFromObj(String filename) {


        ArrayList<Triangle> triangles = new ArrayList<Triangle>();
        ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
        ArrayList<String> materials = new ArrayList<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("v ")) {
                    String[] parts = line.split("\\s+");
                    Vector3f vertex = new Vector3f(Float.parseFloat(parts[1]),
                            Float.parseFloat(parts[2]),
                            Float.parseFloat(parts[3]));

                    vertices.add(vertex);
                } else if (line.startsWith("f ")) {
                    String[] parts = line.split("\\s+");
                    Vector3f v1 = null;
                    Vector3f v2 = null;
                    Vector3f v3 = null;
                    Vector3f normal = null;
                    for (int i = 1; i <= 3; i++) {
                        String[] subParts = parts[i].split("/");
                        int vertexIndex = Integer.parseInt(subParts[0]) - 1;
                        Vector3f vertex = vertices.get(vertexIndex);
                        if (normal == null) {
                            // if this is the first vertex, initialize the normal vector
                            normal = new Vector3f(vertex);
                        } else {
                            // otherwise, add the vertex to the normal vector
                            normal.add(vertex);
                        }
                        if (i == 1) {
                            v1 = vertex;
                        } else if (i == 2) {
                            v2 = vertex;
                        } else if (i == 3) {
                            v3 = vertex;
                        }
                    }
                    normal.div(3.0f);
                    normal.normalize();
                    String materialName = materials.get(materials.size() - 1);
                    Triangle triangle = new Triangle(v1, v2, v3, normal, materialName);
                    triangles.add(triangle);
                } else if (line.startsWith("usemtl ")) {
                    String[] parts = line.split("\\s+");
                    String materialName = parts[1];
                    materials.add(materialName);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return triangles.toArray(new Triangle[triangles.size()]);
    }
}
