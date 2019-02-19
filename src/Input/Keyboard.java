package Input;

import Logging.LogLevel;

import java.awt.*;

import static Logging.Logger.writeLogMessage;

public class Keyboard {

    public void keyboardInput(Robot robot){
        robot.mouseMove(1280, 720);
        writeLogMessage(" x: " + MouseInfo.getPointerInfo().getLocation().x + " y: " + MouseInfo.getPointerInfo().getLocation().y, LogLevel.level.DEBUG);
    }
}
