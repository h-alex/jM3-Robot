package Model;

import Utils.Utils;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import Utils.XYZAxes;

public class Limb {
    private static final ColorRGBA UPPER_PART_COLOR = ColorRGBA.Brown;
    private static final ColorRGBA LOWER_PART_COLOR = ColorRGBA.Blue;


    private Node upperPivot;
    private BodyPart upperPart;
    private Node lowerPivot;
    private BodyPart lowerPart;

    private PivotSphere upperSphere;
    private PivotSphere lowerSphere;

    /**
     * @param startPosition : We start drawing downwards from this position on
     * @param assetManager  : Only needed for the material.
     * @param upperPartSize : The size of the upper part of the limb
     * @param lowerPartSize : the size of the lower part of the limb
     */
    public Limb(Vector3f startPosition, Node rootNode, AssetManager assetManager, Vector3f upperPartSize, Vector3f lowerPartSize) {
        upperPivot = new Node("upperPivot");
        upperPivot.setLocalTranslation(startPosition);

        //new XYZAxes(assetManager).attachCoordinateAxes(upperPivot);
        /* Upper Sphere */
        upperSphere = new PivotSphere();
        upperSphere.sphere = new Sphere(10, 100, PivotSphere.PIVOT_SPHERE_RADIUS);
        upperSphere.geometry = new Geometry("Sphere", this.upperSphere.sphere);
        upperSphere.material = new Material(assetManager, Utils.ACCES_MANAGER_LINK);
        upperSphere.material.setColor("Color", ColorRGBA.White);
        upperSphere.geometry.setMaterial(upperSphere.material);
        upperSphere.geometry.setLocalTranslation(Vector3f.ZERO);

        /* Upper Limb */
        upperPart = new BodyPart();
        upperPart.box = new Box(
                upperPartSize.x,
                upperPartSize.y,
                upperPartSize.z
        );
        upperPart.geometry = new Geometry("upperPart", upperPart.box);
        upperPart.material = new Material(assetManager, Utils.ACCES_MANAGER_LINK);
        upperPart.material.setColor("Color", UPPER_PART_COLOR);
        upperPart.geometry.setMaterial(upperPart.material);
        upperPart.geometry.setLocalTranslation(0, -upperPartSize.y, 0);

        /* Lower Sphere */
        lowerPivot = new Node("lowerPivot");
        lowerPivot.setLocalTranslation(0, -2 * upperPartSize.y, 0);

        lowerSphere = new PivotSphere();
        lowerSphere.sphere = new Sphere(10, 100, PivotSphere.PIVOT_SPHERE_RADIUS);
        lowerSphere.geometry = new Geometry("Sphere", this.upperSphere.sphere);
        lowerSphere.material = new Material(assetManager, Utils.ACCES_MANAGER_LINK);
        lowerSphere.material.setColor("Color", ColorRGBA.White);
        lowerSphere.geometry.setMaterial(upperSphere.material);
        lowerSphere.geometry.setLocalTranslation(Vector3f.ZERO);

        /* Lower Part */
        lowerPart = new BodyPart();
        lowerPart.box = new Box(
                lowerPartSize.x,
                lowerPartSize.y,
                lowerPartSize.z
        );
        lowerPart.geometry = new Geometry("lowerPart", lowerPart.box);
        lowerPart.material = new Material(assetManager, Utils.ACCES_MANAGER_LINK);
        lowerPart.material.setColor("Color", LOWER_PART_COLOR);
        lowerPart.geometry.setMaterial(lowerPart.material);
        lowerPart.geometry.setLocalTranslation(0, -lowerPartSize.y, 0);

    }

    public void attachParent(Node node) {
        this.upperPivot.attachChild(upperSphere.geometry);
        this.upperPivot.attachChild(upperPart.geometry);
        this.lowerPivot.attachChild(lowerPart.geometry);
        this.lowerPivot.attachChild(lowerSphere.geometry);
        this.upperPivot.attachChild(lowerPivot);
        node.attachChild(upperPivot);
    }

    public void rotateUpperPivot(float x, float y, float z) {
        upperPivot.rotate(x, y, z);
    }

    public void rotateLowerPivot(float x, float y, float z) {
        lowerPivot.rotate(x, y, z);
    }

    public float getRotationInDegrees() {
        return this.upperPivot.getLocalRotation().toAngles(null)[0];
    }
}
