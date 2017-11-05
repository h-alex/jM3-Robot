package Controller;

import Model.Body;
import Model.BodyPart;
import Model.Head;
import Model.Limb;
import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class Robot {
    public static final float MOVEMENT_SPEED = .05f;


    private Node rootNode; // Center of our robot
    private Vector3f center;
    private AssetManager assetManager;

    private Head head;
    private Body body;

    private Limb rightArm;
    private Limb leftArm;

    private Limb leftFoot;
    private Limb rightFoot;


    private float handsAngle = 0f;
    private float feetAngle = 0f;

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


    private void rotateLeftArm(float x, float y, float z) {
        this.leftArm.rotateUpperPivot(x, y, z);
        this.leftArm.rotateLowerPivot(-x * 1.5f, -y * 1.5f, -z * 1.5f);
    }

    public void rotate(float x, float y, float z) {
        this.rootNode.rotate(x, y, z);
    }


    public void moveRobotForward(float speed) {
        System.out.println("Angle of rotation:" + leftArm.getRotationInDegrees());

        rootNode.move(0, 0, .03f);

        leftArm.rotateUpperPivot(-speed, 0, 0);
        leftArm.rotateLowerPivot(-speed, 0, 0);
        rightArm.rotateUpperPivot(speed, 0, 0);
        rightArm.rotateLowerPivot(speed, 0, 0);

        rightFoot.rotateUpperPivot(-speed, 0, 0);
        rightFoot.rotateLowerPivot(speed*1.5f,0,0);
        leftFoot.rotateUpperPivot(speed, 0, 0);
        leftFoot.rotateLowerPivot(-speed*1.5f,0,0);
    }


    public void moveRobotBackward(float speed) {

        System.out.println("Angle of rotation:" + leftArm.getRotationInDegrees());
        leftArm.rotateUpperPivot(speed, 0, 0);
        leftArm.rotateLowerPivot(speed, 0, 0);

        rightArm.rotateUpperPivot(-speed, 0, 0);
        rightArm.rotateLowerPivot(-speed, 0, 0);


        rightFoot.rotateUpperPivot(speed, 0, 0);
        rightFoot.rotateLowerPivot(-speed*1.5f,0,0);
        leftFoot.rotateUpperPivot(-speed, 0, 0);

        rootNode.move(0, 0, -.03f);

        leftFoot.rotateLowerPivot(speed*1.5f,0,0);
    }
}
