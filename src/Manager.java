import Input.Keyboard;

import java.awt.*;

public class Manager {

    private static Robot robot;

    public static void main(String[] args){
        final Robot ROBOT = initialiseRobot();

        Keyboard keyboard = new Keyboard();
        keyboard.keyboardInput(robot);
    }

    private static Robot initialiseRobot(){
        if(GraphicsEnvironment.isHeadless())
            return null;

        try {
            robot = new Robot();
        } catch (AWTException e){
            System.out.println(" [ AWTException ] the platform configuration does not allow low-level input control");
        }

        return robot;
    }
}
