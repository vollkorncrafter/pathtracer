package de.oskarschulz;

import org.joml.Vector3f;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        String out = "";
        Path fileName = Path.of("src/test_scene/test.ppm");

        float aspect_ratio = 1 / 1f;
        int image_width = 256;
        int image_height = (int) (image_width / aspect_ratio);
        image_height  = 256;

        int samples = 1;
        float viewport_height = 1;
        float viewport_width = aspect_ratio * viewport_height;
        float focal_length = 1f;
        Vector3f origin = new Vector3f(0, 0, 0);
        Vector3f horizontal = new Vector3f(viewport_width, 0, 0);
        Vector3f vertical = new Vector3f(0, viewport_height, 0);
        Vector3f lower_left_corner = Camera.lowerLeftCorner(origin,horizontal,vertical,focal_length);
        // Render
        out = out + "P3" + "\n";
        out = out + image_width + " " + image_height + "\n";
        out = out + "255" + "\n";

        Camera cam = new Camera();

        Hittables verts = new Hittables();
        verts.tris = OBJLoader.loadTrianglesFromObj("src/test_scene/scene.obj");

        cam.verts = verts;

        for (int j = image_height - 1; j >= 0; j--) {
            long begin = System.currentTimeMillis();
            for (int i = 0; i < image_width; i++) {
                float u = (float) i / (image_width - 1);
                float v = (float) j / (image_height - 1);

                Vector3f point = Camera.pointOnImagePlane(lower_left_corner,horizontal,vertical,origin,u,v);

                Vector3f avg = new Vector3f(0,0,0);

                for(int s = 0; s < samples; s++) {
                    //point.add(Noise.noise(0.00005f));

                    Ray r = new Ray(origin, point);

                    Vector3f px = cam.renderPixel(r,1);
                    avg.add(px);
                }

                avg.div(samples);

                String c = Color.toRGBstring(avg);
                out += c + "\n";

            }
            System.out.println("line" + j);

            long end = System.currentTimeMillis();
            long dt = end - begin;
            System.out.println(dt);

        }

        Files.writeString(fileName, out);

        String file_content = Files.readString(fileName);
    }
}
