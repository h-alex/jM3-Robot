package Model.Structs;

import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

/**
 * Created by alex on 04.11.2017.
 */
public class PivotSphere {
    public static final Float PIVOT_SPHERE_RADIUS = .1f;

    public Sphere sphere;
    public Geometry geometry;
    public Material material;
}
