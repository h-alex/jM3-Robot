package Model;

import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;


/**
 * Created by alex on 04.11.2017.
 */
public class BodyPart {
    public static final Vector3f HEAD_SIZE = new Vector3f(.5f, .3f, .1f);
    public static final Vector3f NECK_SIZE = new Vector3f(.1f, .1f, .1f);

    public static final Vector3f BODY_SIZE = new Vector3f(.75f,1,.3f);

    public static final Vector3f ARM_SIZE = new Vector3f(0.3f, 0.3f, .3f);
    public static final Vector3f FOREARM_SIZE = new Vector3f(.3f, .3f, .3f);

    public static final Vector3f FOOT_SIZE = new Vector3f(0.3f, 0.3f, .3f);
    public static final Vector3f FOREFOOT_SIZE = new Vector3f(.3f, .3f, .3f);


    public static final float BODY_PARTS_SPACE = .125f;


    public Box box;
    public Geometry geometry;
    public Material material;
}
