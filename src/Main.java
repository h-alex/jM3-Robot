import Controller.Robot;
import Utils.XYZAxes;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;

public class Main extends SimpleApplication {
    private XYZAxes xyzAxes;
    private Robot robot;

    public static void main(String[] args) {
        System.out.println("Starting the jMEngine3...");
        Main app = new Main();
        app.start();
        System.out.println("Closing the jMEngine3...");
    }

    @Override
    public void simpleInitApp() {
        // Test multiple inputs per mapping
        this.speed = 0.1f;
        cam.setLocation(new Vector3f(-10f, 5f, 10f));
        robot = new Robot(rootNode, assetManager, Vector3f.ZERO);
        xyzAxes = new XYZAxes(this.assetManager);
        xyzAxes.attachCoordinateAxes(rootNode);
        robot.assembleRobot();
        this.addActionListeners();
    }

    private void addActionListeners() {
        inputManager.addMapping("move_forward", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("move_backward", new KeyTrigger(KeyInput.KEY_K));

        inputManager.addListener(analogListener, new String[]{"move_forward", "move_backward"});
    }


    // We use AnalogListener because we want continuous movement.
    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            if (name.equals("move_forward")){
                System.out.println("We are moving forward: name= " + name + " | value= " + value + " | tpf= " + tpf);
                robot.moveRobotForward(speed);
            } else if (name.equals("move_backward")){
                System.out.println("We are moving backwards: name= " + name + " | value= " + value + " | tpf= " + tpf);
                robot.moveRobotBackward(speed);
            }
        }
    };

}