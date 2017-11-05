package Utils;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.debug.Arrow;

/**
 * Created by alex on 04.11.2017.
 */
public class XYZAxes {
    private static final int ARROW_THICKNESS = 1;
    private static final float AXE_LENGTH = 7.5f;
    AssetManager assetManager;

    public XYZAxes(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    private void putShape(Node n, Mesh shape, ColorRGBA color) {
        Geometry g = new Geometry("coordinate axis", shape);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.getAdditionalRenderState().setWireframe(true);
        mat.setColor("Color", color);
        g.setMaterial(mat);
        n.attachChild(g);
    }

    public void attachCoordinateAxes(Node n) {
        Arrow arrow = new Arrow(new Vector3f(AXE_LENGTH, 0f, 0f));
        arrow.setLineWidth(ARROW_THICKNESS); // make arrow thicker
        putShape(n, arrow, ColorRGBA.White);



        arrow = new Arrow(new Vector3f(-1 * AXE_LENGTH,0f, 0f));
        arrow.setLineWidth(ARROW_THICKNESS); // make arrow thicker
        putShape(n, arrow, ColorRGBA.White);


        arrow = new Arrow(new Vector3f(0f, AXE_LENGTH, 0f));
        arrow.setLineWidth(ARROW_THICKNESS); // make arrow thicker
        putShape(n, arrow, ColorRGBA.White);

        arrow = new Arrow(new Vector3f(0f, -1 * AXE_LENGTH, 0f));
        arrow.setLineWidth(ARROW_THICKNESS); // make arrow thicker
        putShape(n, arrow, ColorRGBA.White);

        arrow = new Arrow(new Vector3f(0f, 0f, AXE_LENGTH));
        arrow.setLineWidth(ARROW_THICKNESS); // make arrow thicker
        putShape(n, arrow, ColorRGBA.White);

        arrow = new Arrow(new Vector3f(0f, 0f, -1 * AXE_LENGTH));
        arrow.setLineWidth(ARROW_THICKNESS); // make arrow thicker
        putShape(n, arrow, ColorRGBA.White);
    }
}
