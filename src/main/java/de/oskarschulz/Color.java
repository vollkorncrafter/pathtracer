package de.oskarschulz;

import org.joml.Vector3f;

public class Color {
    public static String toRGBstring(Vector3f out){
        int ir = Math.round(out.x());
        int ig = Math.round(out.y());
        int ib = Math.round(out.z());

        if(ir > 255 || ig >255 || ib > 255){
            ir = 255;
            ig = 255;
            ib = 255;
        }

        return String.valueOf(ir) + " "+ String.valueOf(ig) +" "+ String.valueOf(ib);
    }

}
