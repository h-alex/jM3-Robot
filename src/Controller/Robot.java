package Controller;

import Model.Body;
import Model.Head;
import Model.Limb;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 * Created by alex on 04.11.2017.
 */
public class Robot {
    private Node rootNode; // Center of our robot
    public Vector3f center;
    public AssetManager assetManager;

    private Head head;
    private Body body;

    private Limb rightArm;
    private Limb leftArm;

    private Limb leftFoot;
    private Limb rightFoot;


    public Robot(Node node, AssetManager assetManager, Vector3f center) {
        this.rootNode = node.clone(false);
        node.attachChild(this.rootNode);
        this.assetManager = assetManager;
        this.center = center;
    }

    public void assembleRobot() {
        body = new Body(this.center, this.assetManager);
        head = new Head(this.center, this.assetManager);


        head.attachParent(this.rootNode);
        body.attachParent(this.rootNode);
    }


}
