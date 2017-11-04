import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import javafx.scene.paint.Color;
import com.jme3.math.ColorRGBA;


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