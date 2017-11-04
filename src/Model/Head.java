package Model;

import Controller.Robot;
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
public class Head {
    private static final ColorRGBA NECK_COLOR = ColorRGBA.White;
    private static final ColorRGBA HEAD_COLOR = ColorRGBA.DarkGray;

    private BodyPart head;
    private BodyPart neck;


    /*
    * Location of head: centerOfRobot + sizeOfBody/2
    * */
    public Head(Vector3f centerOfRobot, AssetManager assetManager) {
        head = new BodyPart();
        neck = new BodyPart();

        neck.box = new Box(
                BodyPart.NECK_SIZE.x,
                BodyPart.NECK_SIZE.y,
                BodyPart.NECK_SIZE.z
        );

        neck.geometry = new Geometry("neck", neck.box);
        neck.material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        neck.material.setColor("Color", NECK_COLOR);
        neck.geometry.setMaterial(neck.material);
        // setting position of the neck
        neck.geometry.setLocalTranslation(
                centerOfRobot.x,
                centerOfRobot.y + BodyPart.BODY_SIZE.y + BodyPart.NECK_SIZE.y,
                centerOfRobot.z
        );

        head.box = new Box(
                BodyPart.HEAD_SIZE.x,
                BodyPart.HEAD_SIZE.y,
                BodyPart.HEAD_SIZE.z
        );

        head.geometry = new Geometry("head", head.box);
        head.material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        head.material.setColor("Color", HEAD_COLOR);
        head.geometry.setMaterial(head.material);
        // setting position of the head
        head.geometry.setLocalTranslation(
                centerOfRobot.x,
                centerOfRobot.y + BodyPart.BODY_SIZE.y + BodyPart.NECK_SIZE.y*2f + BodyPart.HEAD_SIZE.y,
                centerOfRobot.z
        );
    }


    public void attachParent(Node node) {
        node.attachChild(neck.geometry);
        node.attachChild(head.geometry);
    }
}
