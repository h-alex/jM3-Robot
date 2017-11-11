package Controller;

import Model.Body;
import Model.Structs.BodyPart;
import Model.Head;
import Model.Limb;
import Utils.Utils;
import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class Robot {
    public static final float ROTATION_ANGLE = 1.0f;
    private static int FORTH = 1;
    private static int BACK = -1;


    private Node rootNode; // Center of our robot
    private Vector3f center;
    private AssetManager assetManager;

    private Head head;
    private Body body;

    private Limb rightArm;
    private Limb leftArm;

    private Limb leftFoot;
    private Limb rightFoot;


    private float rotationAngle = 0f;
    private float direction = -1;

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


    public void rotate(float x, float y, float z) {
        this.rootNode.rotate(x, y, z);
    }


    public void moveRobotForward(float speed) {
        System.out.println("Angle of rotationAngle: \n\t RAD:" + leftArm.getRotationInRadForUpperPivot() + "\n\t DEG:" + leftArm.getRotationInRadForUpperPivot() * FastMath.RAD_TO_DEG);
        FORTH = 1;
        BACK = -1;
        moveRobot(speed);
    }


    public void moveRobotBackward(float speed) {
        System.out.println("Angle of rotation for Upper: \n\t RAD:" + leftArm.getRotationInRadForUpperPivot() + "\n\t DEG:" + leftArm.getRotationInRadForUpperPivot() * FastMath.RAD_TO_DEG);
        System.out.println("Angle of rotation for Lower: \n\t RAD:" + leftArm.getRotationInRadForLowerPivot() + "\n\t DEG:" + leftArm.getRotationInRadForLowerPivot() * FastMath.RAD_TO_DEG);
        BACK = 1;
        FORTH = -1;
        moveRobot(-1.0f * speed);
    }


    private void moveRobot(float speed) {

        rotationAngle = leftArm.getRotationInRadForUpperPivot();
        changeDirections(rotationAngle);

        leftArm.rotateUpperPivotAroundX(direction * speed);
        rightFoot.rotateUpperPivotAroundX(direction * speed);

        rightArm.rotateUpperPivotAroundX(-1 * direction * speed);
        leftFoot.rotateUpperPivotAroundX(-1 * direction * speed);

        /* We move the leftArm as long as its rotation is <0
         */
        System.out.println("\tLeft arm rotation 1: " + leftArm.getRotationInRadForLowerPivot());
        /* TODO Why is this not working?
         * TODO If the lowerPart.angle < 0 and we are moving backward, we shall not move it
         * TODO If the lowerPart.angle >= 0 and */

        if (leftArm.lowerPivotRotationAngle  + direction*speed <=  0
                && leftArm.upperPivotRotationAngle  < 0 &&
                leftArm.upperPivotRotationAngle > -Utils.DEG_45_TO_RAD){
            leftArm.rotateLowerPivotAroundX(speed * direction);
            rightFoot.rotateLowerPivotAroundX( - speed * direction);
        } else {
            rightArm.rotateLowerPivotAroundX(-speed*direction);
            leftFoot.rotateLowerPivotAroundX(speed * direction);
        }


        //    rightArm.rotateLowerPivotAroundX(-1 * direction * speed);

        this.rootNode.move(0, 0, speed);
    }

    /* Here we'll change the direction of the robot, if needed. */
    private void changeDirections(float rotation) {
        /* Because all the limbs are moving in the same time, it's enough to know when
        on of the limbs is out of our limit to change its direction.
        We'll be using `leftArm` for this
         */
        if (rotation > Utils.DEG_45_TO_RAD) {
            direction = BACK;
        } else if (rotation < -1 * Utils.DEG_45_TO_RAD) {
            direction = FORTH;
        }

    }
}
