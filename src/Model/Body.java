package Model;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * Created by alex on 04.11.2017.
 */
public class Body {
    private static final ColorRGBA BODY_COLOR = ColorRGBA.Red;

    private BodyPart body;

    public Body(Vector3f location, AssetManager assetManager) {
        body = new BodyPart();
        body.box = new Box(
                BodyPart.BODY_SIZE.x,
                BodyPart.BODY_SIZE.y,
                BodyPart.BODY_SIZE.z
        );
        body.geometry = new Geometry("body", this.body.box);
        body.material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        body.material.setColor("Color", BODY_COLOR);
        body.geometry.setLocalTranslation(location);
        body.geometry.setMaterial(body.material);

    }

    public void attachParent(Node node) {
        node.attachChild(body.geometry);
    }
}
