package Controller;

import Model.Body;
import Model.BodyPart;
import Model.Head;
import Model.Limb;
import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class Robot {

    private Node rootNode; // Center of our robot
    private Vector3f center;
    private AssetManager assetManager;

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

        /* Left Arm */
        Vector3f startPosition = new Vector3f();
        startPosition.x = center.x + body.getWidth() + BodyPart.ARM_SIZE.x;
        startPosition.y = center.y + body.getHeight();
        startPosition.z = center.z;
        leftArm = new Limb(startPosition.clone(), rootNode, assetManager, BodyPart.ARM_SIZE, BodyPart.FOREARM_SIZE);

        /* Right Arm */
        startPosition.x = -startPosition.x;         // we only need to mirror the X for the other arm.
        rightArm = new Limb(startPosition.clone(), rootNode, assetManager, BodyPart.ARM_SIZE, BodyPart.FOREARM_SIZE);

        /* Left Foot */
        startPosition.x = center.x + body.getWidth() - BodyPart.FOOT_SIZE.x;
        startPosition.y *= -1;                       // we mirror the value because we start from BOTTOM now
        leftFoot = new Limb(startPosition.clone(), rootNode, assetManager, BodyPart.FOOT_SIZE, BodyPart.FOREFOOT_SIZE);

        /* Right Foot */
        startPosition.x *= -1;
        rightFoot = new Limb(startPosition.clone(), rootNode, assetManager, BodyPart.FOOT_SIZE, BodyPart.FOREFOOT_SIZE);


        /* Now we attach the parts. */
        head.attachParent(this.rootNode);
        body.attachParent(this.rootNode);
        leftArm.attachParent(this.rootNode);
        rightArm.attachParent(this.rootNode);
        leftFoot.attachParent(this.rootNode);
        rightFoot.attachParent(this.rootNode);
    }


    public void rotateLeftArm(float x, float y, float z){
        this.leftArm.rotateUpperPivot(x,y,z);
        this.leftArm.rotateLowerPivot(-x*1.5f, -y*1.5f, -z*1.5f);
    }

    public void rotate(float x, float y, float z) {
        this.rootNode.rotate(x, y, z);
    }
}
