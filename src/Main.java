import Controller.Robot;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;


public class Main extends SimpleApplication {
    XYZAxes xyzAxes;
    Robot robot;


    public static void main(String[] args) {
        System.out.println("Hello World!");
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        cam.setLocation(new Vector3f(0f, 1f, 5f));
        robot = new Robot(rootNode, assetManager, Vector3f.ZERO);
        xyzAxes = new XYZAxes(this.assetManager);
        xyzAxes.attachCoordinateAxes(rootNode);

        robot.assembleRobot();
    }
}